package com.lcwd.db.multi_db.mysql.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactManager_seq_gen")
    @SequenceGenerator(name = "contactManager_seq_gen", sequenceName = "contactManager_seq", allocationSize = 1)
    private int id;

    @NotNull(message = "Name field is required!")
    @Size(min = 2,max = 20,message = "min 2 and max 20 characters are allowed!!")
    private  String firstName;

    @Column(unique = true)
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid userEmail format")
    @NotEmpty(message = "Email cannot be empty")
    private String userEmail;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number. It must be exactly 10 digits removing country code!")
    private String mobileNo;

    @NotNull(message = "Role is required!")
    @Pattern(regexp = "^(Admin|User|Moderator)$", message = "Role must be one of: Admin, User, Moderator!")
    private String role;

    @NotNull(message = "Address cannot be null!")
    @Size(min = 5, max = 100, message = "Address must be between 5 and 100 characters!")
    private String address;

    public Users() {
    }

    public Users(int id, String firstName, String userEmail, String mobileNo, String role, String address) {
        this.id = id;
        this.firstName = firstName;
        this.userEmail = userEmail;
        this.mobileNo = mobileNo;
        this.role = role;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "Name field is required!") @Size(min = 2, max = 20, message = "min 2 and max 20 characters are allowed!!") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "Name field is required!") @Size(min = 2, max = 20, message = "min 2 and max 20 characters are allowed!!") String firstName) {
        this.firstName = firstName;
    }

    public @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid userEmail format") @NotEmpty(message = "Email cannot be empty") String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(@Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid userEmail format") @NotEmpty(message = "Email cannot be empty") String userEmail) {
        this.userEmail = userEmail;
    }

    public @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number. It must be exactly 10 digits removing country code!") String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(@Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number. It must be exactly 10 digits removing country code!") String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public @NotNull(message = "Role is required!") @Pattern(regexp = "^(Admin|User|Moderator)$", message = "Role must be one of: Admin, User, Moderator!") String getRole() {
        return role;
    }

    public void setRole(@NotNull(message = "Role is required!") @Pattern(regexp = "^(Admin|User|Moderator)$", message = "Role must be one of: Admin, User, Moderator!") String role) {
        this.role = role;
    }

    public @NotNull(message = "Address cannot be null!") @Size(min = 5, max = 100, message = "Address must be between 5 and 100 characters!") String getAddress() {
        return address;
    }

    public void setAddress(@NotNull(message = "Address cannot be null!") @Size(min = 5, max = 100, message = "Address must be between 5 and 100 characters!") String address) {
        this.address = address;
    }
}
