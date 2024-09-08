package com.example.contact_app.Presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.contact_app.Dao.ContactDao
import com.example.contact_app.R
import com.example.contact_app.Table.Contact
import com.example.contact_app.ui.theme.MesgColor
import com.example.contact_app.ui.theme.PhoneIconColor
import com.example.contact_app.ui.theme.VideoIconColor
import com.example.contact_app.ui.theme.backgroundColor
import com.example.contact_app.ui.theme.fieldColor
import com.example.contact_app.ui.theme.greenIconColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ContactViewScreen(contactNumber: String?, dbObject: ContactDao, navController: NavHostController) {
    var contact by remember { mutableStateOf<Contact?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var showConfirmationDialog by remember { mutableStateOf(false) }

    LaunchedEffect(contactNumber) {
        contactNumber?.let {
            contact = dbObject.getContactByNumber(it)
            isLoading = false
        } ?: run {
            isLoading = false
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
          //  .padding(16.dp),
       , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(160.dp))

        // Camera Icon inside a circular background
        Column(modifier = Modifier.fillMaxWidth()
            .background(
            color = fieldColor,
            shape = RoundedCornerShape(25.dp)),
             horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            Box(
                modifier = Modifier
                    .size(130.dp) // Size of the circular background
                    .background(color = greenIconColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                    contentDescription = null,
                    modifier = Modifier.size(46.dp), // Size of the icon
                    tint = Color.White // Icon color (optional, can be customized)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            // Contact details in the center
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                // .padding(vertical = 16.dp),
                , horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLoading) {
                    Text(text = "Loading...", fontSize = 20.sp, color = Color.Gray)
                } else {
                    contact?.let {
                        Text(
                            text = "Name: ${it.name}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Phone: ${it.number}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Email: ${it.email}",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )


                        // Icons below email
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = {
                                // Handle call action
                            }) {
                                // Rounded background for Call icon
                                Box(
                                    modifier = Modifier
                                        .size(60.dp) // Adjust the size of the circle
                                        .background(
                                            color = PhoneIconColor,
                                            shape = CircleShape
                                        ), // Light Blue background with CircleShape
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_phone_24),
                                        contentDescription = "Call",
                                        tint = Color.White,
                                        modifier = Modifier.size(25.dp) // Icon size
                                    )
                                }
                            }

                            IconButton(onClick = {
                                // Handle message action
                            }) {
                                // Rounded background for Message icon
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .background(
                                            color = MesgColor,
                                            shape = CircleShape
                                        ), // Light Green background with CircleShape
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_message_24),
                                        contentDescription = "Message",
                                        tint = Color.White,
                                        modifier = Modifier.size(25.dp)
                                    )
                                }
                            }

                            IconButton(onClick = {
                                // Handle video call action
                            }) {
                                // Rounded background for Video Call icon
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .background(color = VideoIconColor, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_videocam_24),
                                        contentDescription = "Video Call",
                                        tint = Color.White,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }

                    } ?: run {
                        Text(text = "No contact found", fontSize = 20.sp, color = Color.Red)
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(70.dp))



        // Bottom bar with icons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .background(color =fieldColor,shape= RoundedCornerShape(25.dp)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Favorite icon
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = {
                    // Handle favorite action
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                        contentDescription = "Favorite",
                        tint = Color.Black
                    )
                }
                Text(text = "Favorite", fontSize = 14.sp, color = Color.Black)
            }

            // Edit icon
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = {
                    contact?.let {
                        navController.navigate("edit/${it.number}")
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_edit_24),
                        contentDescription = "Edit Contact",
                        tint = Color.Black
                    )
                }
                Text(text = "Edit", fontSize = 14.sp, color = Color.Black)
            }

            // Delete icon
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = {
                    showConfirmationDialog = true
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_delete_24),
                        contentDescription = "Delete Contact",
                        tint = Color.Black
                    )
                }
                Text(text = "Delete", fontSize = 14.sp, color = Color.Black)
            }


            // Back button icon
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier
                       // .padding(top = 16.dp)
                    // .align(Alignment.Start)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24), // Replace with your back icon resource
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Text(text = "Back", fontSize = 14.sp, color = Color.Black)

            }
        }
    }
    // Confirmation dialog
    if (showConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmationDialog = false },
            title = { Text(text = "Confirm Delete") },
            text = { Text(text = "Are you sure you want to delete this contact?") },
            confirmButton = {
                TextButton(onClick = {
                    contact?.let { contactToDelete ->
                        CoroutineScope(Dispatchers.IO).launch {
                            dbObject.deleteContact(contactToDelete)
                            withContext(Dispatchers.Main) {
                                navController.navigateUp() // Navigate back after deletion
                            }
                        }
                    }
                    showConfirmationDialog = false
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmationDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}
