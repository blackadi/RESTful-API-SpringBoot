package com.blackadi.app.ws.mobileappws.ui.controller;

import java.util.Map;

import javax.validation.Valid;

import com.blackadi.app.ws.mobileappws.ui.model.request.UpdateUserDetailsRequestModel;
import com.blackadi.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.blackadi.app.ws.mobileappws.ui.model.response.UserRest;
import com.blackadi.app.ws.userservice.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users") // control HTTP path and methods http://localhost:8080/users
public class UserController {

    Map<String, UserRest> users;

    /**
     * Dependancy injection, let spring handle creating a new instance from
     * UserServiceImplementation Spring will create an instance of
     * UserServiceImpementation and injected into, this UserController object and
     * make it avaiable to us. To let the framework discover the
     * UserServiceImplementation and autowire it, we need to go to
     * UserServiceImplementation class and declare it as service
     */
    @Autowired()
    UserService userService;

    // Defined CRUD operation for http methods

    // Query string request
    // http://localhost:8080/users?page=2&limit=2
    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "50") int limit,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        return "get users was called with page = " + page + "and limit = " + limit + " and sort = " + sort;
    }

    // Allow the get methods to return json or xml format
    @GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    // Allow POST method to accept request in JSON or XML and also return data
    // formatted in JSON or XML
    // Adding annotation to handle validation using the build in from spring web
    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

        UserRest returnVal = userService.createUser(userDetails);
        return new ResponseEntity<UserRest>(returnVal, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    public UserRest updateUser(@PathVariable String userId,
            @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {

        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFristname(userDetails.getFistName());
        storedUserDetails.setLastName(userDetails.getLastName());

        users.put(userId, storedUserDetails);

        return storedUserDetails;
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        users.remove(userId);

        return ResponseEntity.noContent().build();
    }
}
