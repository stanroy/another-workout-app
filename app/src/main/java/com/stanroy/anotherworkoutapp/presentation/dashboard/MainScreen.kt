package com.stanroy.anotherworkoutapp.presentation.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavController
import com.stanroy.anotherworkoutapp.data.DataStoreKeys
import com.stanroy.anotherworkoutapp.data.readDataStoreValue

@Composable
fun MainScreen(navController: NavController, dataStore: DataStore<Preferences>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by remember { mutableStateOf("") }
        LaunchedEffect(Unit) {
            readDataStoreValue(dataStore, DataStoreKeys.profileName).collect {
                name = it.orEmpty()
            }
        }
        Text(text = name)
    }
}