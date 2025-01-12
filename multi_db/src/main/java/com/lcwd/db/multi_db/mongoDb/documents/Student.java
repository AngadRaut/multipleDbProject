package com.lcwd.db.multi_db.mongoDb.documents;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students_info")
public class Student {
    private Integer id;
    @NotNull
    @NotEmpty
    private String name;
    private String address;

    @Column(unique = true)
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid userEmail format")
    @NotEmpty(message = "Email cannot be empty")
    private String studentEmail;

    public Student() {
    }

    public Student(int id, String name, String address,String studentEmail) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.studentEmail=studentEmail;
    }

    public @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid userEmail format") @NotEmpty(message = "Email cannot be empty") String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(@Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid userEmail format") @NotEmpty(message = "Email cannot be empty") String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
