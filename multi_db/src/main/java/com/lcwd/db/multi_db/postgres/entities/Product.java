package com.lcwd.db.multi_db.postgres.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactManager_seq_gen")
    @SequenceGenerator(name = "contactManager_seq_gen", sequenceName = "contactManager_seq", allocationSize = 1)
    private int id;

    @NotNull(message = "Name field is required!")
    @Size(min = 2,max = 20,message = "min 2 and max 20 characters are allowed!!")
    private String productName;

   /* @NotNull(message = "Order date is required!")
    @PastOrPresent(message = "Order date cannot be in the future!")
    private LocalDate orderDate;*/

    @NotNull(message = "Order from field is required!")
    @Size(min = 3, max = 50, message = "Min 3 and max 50 characters are allowed!")
    private String orderFrom;

    @NotNull(message = "Price is required!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0!")
    @Digits(integer = 10, fraction = 2, message = "Price must have a maximum of 10 integer digits and 2 decimal places!")
    private BigDecimal price;

    @Min(value = 1, message = "Quantity must be at least 1!")
    @Max(value = 1000, message = "Quantity cannot exceed 1000!")
    private int quantity;

    @NotNull(message = "Status is required!")
    @Pattern(regexp = "^(Pending|Shipped|Delivered|Cancelled)$", message = "Status must be one of: Pending, Shipped, Delivered, Cancelled!")
    private String status;

    public Product(int id, String productName, LocalDate orderDate, String orderFrom, BigDecimal price, int quantity, String status) {
        this.id = id;
        this.productName = productName;
//        this.orderDate = orderDate;
        this.orderFrom = orderFrom;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "Name field is required!") @Size(min = 2, max = 20, message = "min 2 and max 20 characters are allowed!!") String getProductName() {
        return productName;
    }

    public void setProductName(@NotNull(message = "Name field is required!") @Size(min = 2, max = 20, message = "min 2 and max 20 characters are allowed!!") String productName) {
        this.productName = productName;
    }

  /*  public @NotNull(message = "Order date is required!") @PastOrPresent(message = "Order date cannot be in the future!") LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(@NotNull(message = "Order date is required!") @PastOrPresent(message = "Order date cannot be in the future!") LocalDate orderDate) {
        this.orderDate = orderDate;
    }*/

    public @NotNull(message = "Order from field is required!") @Size(min = 3, max = 50, message = "Min 3 and max 50 characters are allowed!") String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(@NotNull(message = "Order from field is required!") @Size(min = 3, max = 50, message = "Min 3 and max 50 characters are allowed!") String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public @NotNull(message = "Price is required!") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0!") @Digits(integer = 10, fraction = 2, message = "Price must have a maximum of 10 integer digits and 2 decimal places!") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price is required!") @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0!") @Digits(integer = 10, fraction = 2, message = "Price must have a maximum of 10 integer digits and 2 decimal places!") BigDecimal price) {
        this.price = price;
    }

    @Min(value = 1, message = "Quantity must be at least 1!")
    @Max(value = 1000, message = "Quantity cannot exceed 1000!")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 1, message = "Quantity must be at least 1!") @Max(value = 1000, message = "Quantity cannot exceed 1000!") int quantity) {
        this.quantity = quantity;
    }

    public @NotNull(message = "Status is required!") @Pattern(regexp = "^(Pending|Shipped|Delivered|Cancelled)$", message = "Status must be one of: Pending, Shipped, Delivered, Cancelled!") String getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "Status is required!") @Pattern(regexp = "^(Pending|Shipped|Delivered|Cancelled)$", message = "Status must be one of: Pending, Shipped, Delivered, Cancelled!") String status) {
        this.status = status;
    }
}
