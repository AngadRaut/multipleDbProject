package com.lcwd.db.multi_db.mysql.services;

import com.lcwd.db.multi_db.mysql.entities.Users;
import com.lcwd.db.multi_db.mysql.repo.UsersRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersRepo usersRepo;

    // service method for add user
    @Override
    public Users addUser(Users user) {
        return this.usersRepo.save(user);
    }

    // get all user details from db
    @Override
    public List<Users> getUsers() {
        List<Users> users = this.usersRepo.findAll();
        return users;
    }

    @Override
    public Users getUserById(int id) {
        Optional<Users> users = this.usersRepo.findById(id);
        return users.orElse(null);
    }

    @Override
    public Users updateUser(int id, Users users) {
        Optional<Users> users1 = this.usersRepo.findById(id);
        if(users1.isPresent()){
            Users users2 = users1.get();
            users2.setFirstName(users.getFirstName());
            users2.setAddress(users.getAddress());
            users2.setUserEmail(users.getUserEmail());
            users2.setRole(users.getRole());
            users2.setMobileNo(users.getMobileNo());
            return users2;
        }else
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        Optional<Users> users = this.usersRepo.findById(id);
        if (users.isPresent()) {
            this.usersRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
