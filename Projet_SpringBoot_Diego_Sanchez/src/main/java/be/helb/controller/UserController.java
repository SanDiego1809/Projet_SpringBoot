package be.helb.controller;

import be.helb.dao.UserDao;
import be.helb.model.ApplicationUser;
import be.helb.service.SerieService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody ApplicationUser user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @PostMapping("/login")
    public void theFakeLogin(@RequestBody ApplicationUser loginRequestModel)
    {
        throw new IllegalStateException("This method should not be called.");
    }




}
