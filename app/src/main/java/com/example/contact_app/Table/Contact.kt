package com.example.contact_app.Table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(

    @PrimaryKey var number: String,
    var name: String,
    var email: String
)

