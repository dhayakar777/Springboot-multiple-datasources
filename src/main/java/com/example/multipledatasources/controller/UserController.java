package com.example.multipledatasources.controller;


import com.example.multipledatasources.service.UserService;
import com.example.multipledatasources.user.User;
import com.example.multipledatasources.userrepository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(tags = "User Queries", value = "UserQueries", description = "Controller for users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController( UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add user")
    public User addUser(@RequestBody User user) {
     return userService.addCreateNewUser(user);
    }


    @GetMapping(value = "/all-users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
