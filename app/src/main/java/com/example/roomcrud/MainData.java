package com.example.roomcrud;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Define table name
@Entity(tableName = "records")
public class MainData {

    // create id column
    @PrimaryKey(autoGenerate = true)
    private int id;

    //create name columnm
    @ColumnInfo(name = "name")
    private String name;

    //create amount column
    @ColumnInfo(name = "amount")
    private  String amount;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
