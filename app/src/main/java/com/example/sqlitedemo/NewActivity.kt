package com.example.sqlitedemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class NewActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var dbUtil = DBUtil.getInstance(this)
        dbUtil.addCustomer(
            Customer(103,"Mayuri",909773222)
        )

        dbUtil.addCustomer(
            Customer(104,"Vaishnavi",985639090)
        )

        var customers = dbUtil.getAllCustomers()
        for(eachCustomer in customers){
            Log.e("tag",eachCustomer.toString())
        }
    }
}