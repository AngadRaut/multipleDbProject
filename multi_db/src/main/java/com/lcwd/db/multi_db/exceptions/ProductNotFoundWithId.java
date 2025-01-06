package com.lcwd.db.multi_db.exceptions;


public class ProductNotFoundWithId extends RuntimeException{
    public ProductNotFoundWithId(String string){
        super(string);
    }
}
