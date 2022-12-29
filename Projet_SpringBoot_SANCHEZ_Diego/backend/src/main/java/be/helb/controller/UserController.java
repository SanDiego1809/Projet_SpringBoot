package be.helb.controller;

import be.helb.model.ApplicationUser;
import be.helb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController
{
    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody ApplicationUser user)
    {
        userService.signUp(user);
    }

    @PostMapping("/login")
    public void theFakeLogin(@RequestBody ApplicationUser loginRequestModel)
    {
        throw new IllegalStateException("This method should not be called.");
    }

    @GetMapping("users")
    public ResponseEntity<List<ApplicationUser>> getUsersList()
    {
        try {
            List<ApplicationUser> usersList = userService.getAll();
            if (usersList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user/{name}")
    public ResponseEntity<ApplicationUser> getUserByName(@PathVariable("name") String name)
    {
        try{
            ApplicationUser user = userService.getUserByName(name);
            if(user == null)
            {
                return new ResponseEntity<>(userService.getUserByName(name),HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userService.getUserByName(name),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/username/{username}")
    public ResponseEntity<ApplicationUser> updateUser(@RequestBody ApplicationUser user,@PathVariable("username") String username)
    {
        try{
            return new ResponseEntity<>(userService.updateUsername(user, username),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/username/{username}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("username") String username)
    {
        try {
            userService.deleteUser(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
