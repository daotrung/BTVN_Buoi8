package com.daotrung.btvn_buoi8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameUpdate ,addressUpdate , phoneUpdate ;
    Button btnUpdate ;
    MyDataBaseHelper myDB ;
    QLySV qLySV ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        nameUpdate = findViewById(R.id.name_input_update);
        addressUpdate = findViewById(R.id.address_input_update);
        phoneUpdate = findViewById(R.id.phone_input_update);
        btnUpdate = findViewById(R.id.btn_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDB = new MyDataBaseHelper(UpdateActivity.this);
        qLySV = myDB.getSVByID(getIntent().getExtras().getLong("id"));
        getSupportActionBar().setTitle("Update SV");

        btnUpdate.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if(view == btnUpdate){
            String name  = nameUpdate.getText().toString().trim();
            String address = addressUpdate.getText().toString().trim();
            String phone = phoneUpdate.getText().toString().trim();

            if(!name.isEmpty() && !address.isEmpty() && !phone.isEmpty()){
                long temp_id = 1 ;
                QLySV qLySV = new QLySV(temp_id,name,address,phone);

                MyDataBaseHelper dataBaseHelper = new MyDataBaseHelper(getApplicationContext());
                dataBaseHelper.createSV(qLySV);

                Toast.makeText(getApplicationContext(), "SV update successful" +dataBaseHelper.getAllSV().size(), Toast.LENGTH_SHORT).show();
                nameUpdate.setText("");
                addressUpdate.setText("");
                phoneUpdate.setText("");
            }else{
                Toast.makeText(getApplicationContext(), "Please fill fields to continue", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true ;
        }
        return super.onOptionsItemSelected(item);
    }
}