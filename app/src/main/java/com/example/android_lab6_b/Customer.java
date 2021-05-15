package com.example.android_lab6_b;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Customer {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "diaChi")
    String diaChi;

    @Override
    public String toString() {
        return id + ". " + diaChi;
    }
}
