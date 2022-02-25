package com.example.roomcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //initialize variables
    EditText nameedit, amountedit;
    Button addbtn, resetbtn;
    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    Roomdb database;
    Radapter radapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameedit = findViewById(R.id.nameedit);
        amountedit = findViewById(R.id.amountedit);
        addbtn = findViewById(R.id.addbtn);
        resetbtn = findViewById(R.id.resetbtn);
        recyclerView = findViewById(R.id.recycler);

        // init database
        database = Roomdb.getInstance(this);
        //store value in datalist
        dataList = database.mainDAO().getAllrecords();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        radapter = new Radapter(dataList,MainActivity.this);
        recyclerView.setAdapter(radapter);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get string from edit text
                String stextname = nameedit.getText().toString();
                String stextamount = amountedit.getText().toString();
                // chech condition
                if(!stextname.equals("") && !stextamount.equals("")){

                    MainData data = new MainData();
                    data.setName(stextname);
                    data.setAmount(stextamount);
                    //insert text in database
                    database.mainDAO().insert(data);
                    nameedit.setText("");
                    amountedit.setText("");
                    //notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDAO().getAllrecords());
                    radapter.notifyDataSetChanged();

                }
            }
        });

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete all records
                database.mainDAO().reset(dataList);
                //notify when all data is deleted
                dataList.clear();
                dataList.addAll(database.mainDAO().getAllrecords());
                radapter.notifyDataSetChanged();

            }
        });

    }
}