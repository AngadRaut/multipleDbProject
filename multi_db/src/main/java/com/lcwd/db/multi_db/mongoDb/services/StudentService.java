package com.lcwd.db.multi_db.mongoDb.services;


import com.lcwd.db.multi_db.mongoDb.documents.Student;

public interface StudentService {
    public Student saveEntity(Student student);
}
