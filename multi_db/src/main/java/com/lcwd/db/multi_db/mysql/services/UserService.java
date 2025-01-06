package com.lcwd.db.multi_db.mysql.services;

import com.lcwd.db.multi_db.mysql.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Users addUser(Users user);
    public List<Users> getUsers();
    public Users getUserById(int id);
    public Users updateUser(int id,Users users);
    public boolean deleteUser(int id);
}
