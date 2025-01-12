package com.lcwd.db.multi_db.mongoDb.repo;

import com.lcwd.db.multi_db.mongoDb.documents.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student,Integer> {
}
