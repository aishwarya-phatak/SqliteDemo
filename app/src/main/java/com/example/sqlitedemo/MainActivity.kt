package com.example.sqlitedemo

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sqlitedemo.databinding.ActivityMainBinding
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var db : SQLiteDatabase
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = openOrCreateDatabase(
            "db_products",
            Activity.MODE_PRIVATE,
            null
        )
        try{
            db.execSQL("create table products(_id integer primary key, title text not null,price integer)")
        } catch(e : Exception){
            Log.e("tag","exception is $e")
        }
        insertRecords()
    }
    private fun deleteRecords(){
        var count = db.delete("products",
        "_id in (?,?)",
            arrayOf("1000","101")
        )
    }

    private fun insertRecords(){
        var values = ContentValues()
        values.put("_id",101)
        values.put("title","Product 1")
        values.put("price",1000)

        var rowNum = db.insert("products",null,values)
        if(rowNum >= 0){
            Log.e("tag","Successfully entered Data")
        }
        else{
            Log.e("tag","Data not entered - error")
        }

        var random = Random()
        for(i in 1..20){
            values.put("_id",Math.abs(random.nextInt()))
            values.put("title","Product $i")
            values.put("price",(Math.abs(random.nextInt()) % 1000) * 2)

            db.insert("products",null,values)
        }
    }
}