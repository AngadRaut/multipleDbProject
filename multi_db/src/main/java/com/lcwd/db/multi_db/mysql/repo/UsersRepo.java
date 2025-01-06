package com.lcwd.db.multi_db.mysql.repo;

import com.lcwd.db.multi_db.mysql.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {
}
