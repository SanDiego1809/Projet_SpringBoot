package be.helb.service;

import be.helb.dao.UserDao;
import be.helb.model.ApplicationUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserDao userDao,BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userDao = userDao;
        this.bCryptPasswordEncoder =bCryptPasswordEncoder;
    }

    public void signUp(ApplicationUser user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    public List<ApplicationUser> getAll()
    {
        return userDao.findAll();
    }
    public ApplicationUser getUserByName(String name)
    {
        return userDao.findByUsername(name);
    }

    public ApplicationUser updateUsername(ApplicationUser user, String username)
    {
        ApplicationUser depDB = userDao.findByUsername(username);
        depDB.setUsername(user.getUsername());

        return userDao.save(depDB);
    }

    public void deleteUser(String username)
    {
        ApplicationUser user = userDao.findByUsername(username);
        userDao.delete(user);
    }
}
