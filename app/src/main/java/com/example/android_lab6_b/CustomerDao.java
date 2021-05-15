package com.example.android_lab6_b;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CustomerDao {
    @Query("SELECT * FROM Customer")
    List<Customer> getAll();

    @Insert
    void insertCustomer(Customer customer);

    @Delete
    void delete(Customer customer);

    @Query("UPDATE Customer SET diaChi = :diaChi WHERE id = :ID")
    void update(int ID, String diaChi);

    @Query("SELECT * FROM Customer WHERE id = :id ")
    Customer getById(int id);
}
