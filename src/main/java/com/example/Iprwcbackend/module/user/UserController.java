package com.example.Iprwcbackend.module.user;

import com.example.Iprwcbackend.module.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("{userId}")
    public Optional<User> getUser(@PathVariable("userId") Long userId){ return userService.getUser(userId);}

    @PostMapping()
    public void registerNewUser(@RequestBody User user){
        System.out.println(user);
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        System.out.println(userId);
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                           @RequestBody User user){
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        String email = user.getEmail();
        userService.updateUser(userId, firstName, lastName, password, email);

    }
}
