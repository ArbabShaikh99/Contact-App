package com.example.contact_app.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contact_app.Dao.ContactDao
import com.example.contact_app.Table.Contact

@Database(entities = [Contact::class], version = 2)
abstract class ContactDataBase :RoomDatabase(){

    abstract fun dao():ContactDao


}