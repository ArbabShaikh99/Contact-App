package com.example.contact_app.Presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.contact_app.Dao.ContactDao
import com.example.contact_app.R
import com.example.contact_app.ui.theme.DeepCharcoal
import com.example.contact_app.ui.theme.backgroundColor
import com.example.contact_app.ui.theme.fieldColor

@Composable
fun MainScreen(dbObject: ContactDao, navController: NavHostController) {

    val contacts = dbObject.getAllContact()
    val TotalContact = contacts.size

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_edit_screen") },
                containerColor = DeepCharcoal
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp))
            }
        }
    ) {

        Row(
            modifier = Modifier.fillMaxSize().background(backgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(45.dp))

                Text(
                    text = "Phone",
                    modifier = Modifier,
                    fontSize = 37.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    letterSpacing = 1.5.sp,
                    fontFamily = FontFamily.Serif,
                )
                Text(text = "$TotalContact contacts with phone number")

                Spacer(modifier = Modifier.height(25.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .padding(it)
                ) {
                    items(dbObject.getAllContact()) {  // items: List<T>

                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.LightGray),
                                onClick = {

                                    navController.navigate("contact_view_screen/${it.number}") // Pass the contact number here
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(fieldColor)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.baseline_account_circle_24),
                                        contentDescription = null,
                                        tint = Color.DarkGray,
                                        modifier = Modifier
                                            .size(50.dp)
                                            .padding(5.dp),
                                    )
                                    Column(
                                        modifier = Modifier
                                    ) {
                                        Text(
                                            text = it.name,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.W500

                                        )
                                        Text(
                                            text = it.number,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.W400
                                        )
                                    }


                                     Spacer(modifier = Modifier.weight(1f)) // Spacer to push the icon to the end
                                    
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp) // Size of the box
                                          //  .background(color= DeepCharcoal, shape = RoundedCornerShape(50)) // Rounded corners with black background
                                            .padding(8.dp) // Padding inside the box
                                            .clickable {
                                                // Handle the call action, e.g., open the dialer
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.baseline_phone_24), // Replace with your call icon resource
                                            contentDescription = "Call",
                                            tint = DeepCharcoal, // White color for the icon
                                            modifier = Modifier.size(40.dp) // Size of the icon
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}