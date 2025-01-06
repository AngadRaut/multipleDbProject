package com.lcwd.db.multi_db.mysql.controllers;

import com.lcwd.db.multi_db.mysql.entities.Users;
import com.lcwd.db.multi_db.mysql.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // controller for adding student
    @PostMapping("/add")
    public ResponseEntity<Users> addUser(@Valid @RequestBody Users user){
        Users createdResource = this.userService.addUser(user);
        // Return the created resource with a 201 (created) status code
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdResource);
    }

    // handler for fetching the all user details from the table
    @GetMapping("/allusers")
    public ResponseEntity<List<Users>> fetchUsers() {
        List<Users> usersList = this.userService.getUsers();
        if (usersList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(usersList);
        }
    }
    // fetch details of product with id
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") int id){
        Users user = this.userService.getUserById(id);
        if(user != null){
            return ResponseEntity.status(HttpStatus.FOUND).body(user);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // update the user of particular id
    @PutMapping("/update/{id}")
    public ResponseEntity<Users> updateUser(@Valid @RequestParam int id,@RequestBody Users users){
        Users users1 = this.userService.updateUser(id,users);
        if(users != null){
            return ResponseEntity.status(HttpStatus.FOUND).body(users1);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    // delete product from the database
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")int id){
        boolean isPresent = this.userService.deleteUser(id);
        if(isPresent){
            return ResponseEntity.status(HttpStatus.OK).body("deleted Successfully.....");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Records of this id are not available in database...");
        }
    }
}