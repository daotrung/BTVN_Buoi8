package com.daotrung.btvn_buoi8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name_input , address_input , phone_input ;
    Button btnAdd ;
    private static final String TAG = "CreateSVActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create new sv");
        name_input = findViewById(R.id.name_input);
        address_input = findViewById(R.id.address_input);
        phone_input = findViewById(R.id.phone_input);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
         if(view == btnAdd){
              String name  = name_input.getText().toString().trim();
              String address = address_input.getText().toString().trim();
              String phone = phone_input.getText().toString().trim();

              if(!name.isEmpty() && !address.isEmpty() && !phone.isEmpty()){
                   long temp_id = 1 ;
                   QLySV qLySV = new QLySV(temp_id,name,address,phone);

                   MyDataBaseHelper dataBaseHelper = new MyDataBaseHelper(getApplicationContext());
                   dataBaseHelper.createSV(qLySV);

                  Toast.makeText(getApplicationContext(), "SV create successful" +dataBaseHelper.getAllSV().size(), Toast.LENGTH_SHORT).show();
                  name_input.setText("");
                  address_input.setText("");
                  phone_input.setText("");
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