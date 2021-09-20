package com.daotrung.btvn_buoi8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements QLySVAdapter.ItemClickListener{
    RecyclerView rvSV ;
    ArrayList<QLySV> arrayList ;
    QLySVAdapter adapter ;
    ImageButton btn_add_main ;
    MyDataBaseHelper myDB ;
    int totalSV = 0 ;
    ArrayList<String> student_id , student_name , student_address , student_phone ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new MyDataBaseHelper(MainActivity.this);
        rvSV = findViewById(R.id.rvSV);
        rvSV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadSV();
        btn_add_main = findViewById(R.id.btn_add_main);
        btn_add_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        if(totalSV != myDB.getAllSV().size()){
            arrayList.clear();
            loadSV();
        }
        super.onResume();
    }

    private void loadSV() {

        arrayList = myDB.getAllSV();
        totalSV = arrayList.size();

        if(totalSV == 0){
            Toast.makeText(getApplicationContext(), "No SV found", Toast.LENGTH_SHORT).show();
        }
        adapter = new QLySVAdapter(arrayList,getApplicationContext());
        adapter.setClickListener(this);
        rvSV.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        final QLySV qLySV = adapter.getItemid(position);
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(qLySV.getName())
                .setMessage("Ban muon Update hay Delete ?")
                .setPositiveButton("Update ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(),UpdateActivity.class);
                        intent.putExtra("id",qLySV.getId());
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "SV Deleted Successfully", Toast.LENGTH_SHORT).show();
                        myDB.deleteEvent(qLySV);
                        arrayList.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .setCancelable(true)
                .show();
    }
}