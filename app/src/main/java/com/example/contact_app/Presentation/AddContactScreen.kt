package com.example.contact_app.Presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contact_app.Dao.ContactDao
import com.example.contact_app.R
import com.example.contact_app.Table.Contact
import com.example.contact_app.ui.theme.IconColor
import com.example.contact_app.ui.theme.backgroundColor
import com.example.contact_app.ui.theme.fieldColor





@Preview(showSystemUi = true)
@Composable
fun AddEditContactScreen(dbObject: ContactDao, navController: NavHostController) {

    var Name by remember { mutableStateOf("") }
    var Email by remember { mutableStateOf("") }
    var Number by remember { mutableStateOf("") }
    var group by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.height(22.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        // Camera Icon inside a circular background
        Box(
            modifier = Modifier
                .size(110.dp) // Size of the circular background
                .background(color = IconColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                contentDescription = null,
                modifier = Modifier.size(40.dp), // Size of the icon
                tint = Color.White // Icon color (optional, can be customized)
            )
        }

        Spacer(modifier = Modifier.height(21.dp))

        // Name Field
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {

                OutlinedTextField(
                    value = Name,
                    onValueChange = { Name = it },
                    placeholder = { Text(text = "Name") },

                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_account_circle_24),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier
                        .background(fieldColor)
                        .fillMaxWidth()
                    //.padding(top = 6.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(14.dp))

        // Number Field
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {


                OutlinedTextField(
                    value = Number,
                    onValueChange = { Number = it },
                    placeholder = { Text(text = "Phone") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_phone_24),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier
                        .background(fieldColor)
                        .fillMaxWidth()
                    //.padding(top = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(14.dp))

        // Email Field
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                OutlinedTextField(
                    value = Email,
                    onValueChange = { Email = it },
                    placeholder = { Text(text = "Email") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier
                        .background(fieldColor)
                        .fillMaxWidth()
                    // .padding(top = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(14.dp))

        // Group Field
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                OutlinedTextField(
                    value = group,
                    onValueChange = { group = it },
                    placeholder = { Text(text = "Groups") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_groups_24),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier
                        .background(fieldColor)
                        .fillMaxWidth()
                    //.padding(top = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(50.dp))


        // Save and cancel button  Button

        Row {
            Button(onClick = {
                dbObject.saveUpdateContact(
                    Contact(
                        name = Name,
                        email = Email,
                        number = Number
                    )
                )
                navController.navigateUp()
            }) {
                Text(text = "Save ")
            }
            Spacer(modifier = Modifier.width(19 .dp))

            Button(onClick = {
                navController.navigate( "main_screen") // Navigate to the main screen without saving
            }) {
                Text(text = "Cancel")
            }
        }
    }
}