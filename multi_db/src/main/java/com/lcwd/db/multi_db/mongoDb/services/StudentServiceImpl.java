package com.lcwd.db.multi_db.mongoDb.services;

import com.lcwd.db.multi_db.mongoDb.documents.Student;
import com.lcwd.db.multi_db.mongoDb.repo.StudentRepo;
import com.lcwd.db.multi_db.mysql.controllers.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Student saveEntity(Student student) {
       Student student1 =  this.studentRepo.insert(student);
       return student1;
    }
}
