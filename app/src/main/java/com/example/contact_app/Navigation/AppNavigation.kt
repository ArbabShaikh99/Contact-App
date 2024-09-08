package com.example.contact_app.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.contact_app.Dao.ContactDao
import com.example.contact_app.Presentation.AddEditContactScreen
import com.example.contact_app.Presentation.ContactViewScreen
import com.example.contact_app.Presentation.MainScreen

@Composable
fun AppNavigation(dbObject: ContactDao) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {

        composable(Routes.MAIN_SCREEN) {
            MainScreen(dbObject = dbObject, navController = navController)
        }

        composable(Routes.ADD_EDIT_SCREEN) {
            AddEditContactScreen(dbObject = dbObject, navController = navController)
        }

        composable("contact_view_screen/{contactNumber}") { backStackEntry ->
            val contactNumber = backStackEntry.arguments?.getString("contactNumber")
            ContactViewScreen(contactNumber = contactNumber, dbObject = dbObject, navController = navController)
        }
    }
}
