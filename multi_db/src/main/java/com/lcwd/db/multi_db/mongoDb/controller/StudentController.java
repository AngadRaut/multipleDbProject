package com.lcwd.db.multi_db.mongoDb.controller;

import com.lcwd.db.multi_db.mongoDb.documents.Student;
import com.lcwd.db.multi_db.mongoDb.services.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    private StudentServiceImpl studentService;

    // handler method for saving the student details
    @PostMapping("save_student")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        Student student1 = this.studentService.saveEntity(student);
        logger.info("student details saved successfully.....");
        return ResponseEntity.status(HttpStatus.CREATED).body(student1);
    }
}
