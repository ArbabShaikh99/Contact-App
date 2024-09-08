package com.example.contact_app.DataBase

import androidx.room.Room
import com.example.contact_app.MainActivity

object DataBaseInstance {

    var db : ContactDataBase? = null

    fun getDB(context: MainActivity): ContactDataBase {
        if(db ==null) {
            return Room.databaseBuilder(
                context,
                ContactDataBase::class.java,
                "Contact_DB"
            )
                .allowMainThreadQueries().build()
        }
        return db!!
    }
}


