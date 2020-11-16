package com.github.nguyenvandai61.interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Map;

public interface IDatabaseCommand<T> {
    String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost:3306/bank";
    String ID = "bank";
    String PASS = "123";

    ArrayList<T> getAll();

    T get(Long id);

    void add(T t);

    void update(T t);

    void delete(long id);

    ArrayList<String> getAllName();

    default Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, ID, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
}
