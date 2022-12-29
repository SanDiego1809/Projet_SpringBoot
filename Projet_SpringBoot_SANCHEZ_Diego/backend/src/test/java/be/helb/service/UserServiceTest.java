package be.helb.service;

import be.helb.dao.UserDao;
import be.helb.model.ApplicationUser;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest
{
    private UserDao userDaoMock;
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    public void testGetAllAuthors()
    {
        List<ApplicationUser> usersList = List.of(new ApplicationUser(), new ApplicationUser());

        userDaoMock = EasyMock.mock(UserDao.class);
        EasyMock.expect(userDaoMock.findAll()).andReturn(usersList);

        userService = new UserService(userDaoMock, bCryptPasswordEncoder);
        EasyMock.replay(userDaoMock);
        List<ApplicationUser> result = userService.getAll();
        EasyMock.verify(userDaoMock);
        assertEquals(usersList, result);
    }
    @Test
    public void testGetAllSeriesByGenre()
    {
        ApplicationUser user = new ApplicationUser();

        user.setUsername("test");
        user.setPassword("test2");

        userDaoMock = EasyMock.mock(UserDao.class);
        EasyMock.expect(userDaoMock.findByUsername("test")).andReturn(user);

        userService = new UserService(userDaoMock, bCryptPasswordEncoder);
        EasyMock.replay(userDaoMock);
        ApplicationUser result = userService.getUserByName("test");
        EasyMock.verify(userDaoMock);

        assertEquals(user, result);
    }
}
