package com.daotrung.btvn_buoi8;

import android.util.Log;

public class QLySV {

    public static final String TABLE_NAME = "student_manager";
    public static final String COLUMN_ID = "_id" ;
    public static final String COLUMN_NAME = "student_name";
    public static final String COLUMN_ADDRESS = "student_address";
    public static final String COLUMN_PHONE = "student_phone";

    public static final String CREATE_SV_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " VARCHAR,"
                    + COLUMN_ADDRESS + " VARCHAR,"
                    + COLUMN_PHONE + " VARCHAR);";

    public long id ;
    public String name ;
    public String address ;
    public String phone ;


    public QLySV() {
    }

    public QLySV(long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        Log.d("reponse ","Name : "+name);
        return super.toString();
    }
}
