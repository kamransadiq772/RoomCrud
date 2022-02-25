package com.example.roomcrud;

import android.app.Activity;
import android.app.Dialog;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class Radapter extends RecyclerView.Adapter<Radapter.MyViewHolder> {

    private List<MainData> dataList;
    private Activity context;
    private Roomdb database;

    //constructor


    public Radapter(List<MainData> dataList, Activity context) {
        this.dataList = dataList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MainData mainData = dataList.get(position);
        //Initialize database
        database = Roomdb.getInstance(context);
        //set text
        holder.name.setText(mainData.getName());
        holder.amount.setText(mainData.getAmount());

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize main data
                MainData d = dataList.get(holder.getAdapterPosition());
                //get id
                int sID = d.getId();
                String sname = d.getName();
                String samount = d.getAmount();

                //create dialoge
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialogview);

                //Initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                //Initialize hieght
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                //set Layout
                dialog.getWindow().setLayout(width,height);
                //show dialog
                dialog.show();

                //Initialize and assign variables
                EditText dnameedit = dialog.findViewById(R.id.dnameedit);
                EditText damountedit = dialog.findViewById(R.id.damountedit);
                Button dupdate = dialog.findViewById(R.id.dupdatebtn);

                //set text on edit text
                dnameedit.setText(sname);
                damountedit.setText(samount);

                dupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Dismiss dialog
                        dialog.dismiss();
                        //Get updated text form edit text
                        String dnametext = dnameedit.getText().toString();
                        String damounttext = damountedit.getText().toString();
                        //update text in database
                        database.mainDAO().update(sID,dnametext,damounttext);
                        // notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDAO().getAllrecords());
                        notifyDataSetChanged();
                    }
                });

            }
        });


        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainData d = dataList.get(holder.getAdapterPosition());
                database.mainDAO().delete(d);
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);

                notifyItemRangeChanged(position, dataList.size());

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, amount;
        ImageView deletebtn, editbtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nametv);
            amount = itemView.findViewById(R.id.amounttv);
            deletebtn = itemView.findViewById(R.id.bt_delete);
            editbtn = itemView.findViewById(R.id.bt_edit);

        }
    }
}
