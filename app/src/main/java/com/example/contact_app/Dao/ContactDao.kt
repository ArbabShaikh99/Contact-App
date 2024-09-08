package com.example.contact_app.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contact_app.Table.Contact


@Dao
interface ContactDao {

    @Upsert
    fun saveUpdateContact(contact:Contact)

    @Delete
    fun deleteContact(contact:Contact)

    @Query("SELECT * FROM contact")
    fun getAllContact(): List<Contact>

    @Query("SELECT * FROM contact WHERE number = :number")
    suspend  fun getContactByNumber(number: String): Contact?


}