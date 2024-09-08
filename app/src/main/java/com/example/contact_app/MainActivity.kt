package com.example.contact_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.contact_app.DataBase.DataBaseInstance
import com.example.contact_app.Navigation.AppNavigation
import com.example.contact_app.ui.theme.Contact_AppTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val dbObject =  DataBaseInstance.getDB(this).dao()

            Contact_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box (modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)){
                        AppNavigation(dbObject)


                    }

                }
            }
        }
    }
}
