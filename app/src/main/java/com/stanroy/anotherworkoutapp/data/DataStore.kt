package com.stanroy.anotherworkoutapp.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

object DataStoreKeys {
    val finishedOnboarding = booleanPreferencesKey("FINISHED_ONBOARDING")
    val profileName = stringPreferencesKey("PROFILE_NAME")
}

fun <T> saveDataStoreValue(
    coroutineScope: CoroutineScope,
    dataStore: DataStore<Preferences>,
    key: Preferences.Key<T>,
    value: T
) {
    coroutineScope.launch {
        dataStore.edit {
            it[key] = value
        }
    }
}

fun <T> readDataStoreValue(
    dataStore: DataStore<Preferences>,
    key: Preferences.Key<T>,
): Flow<T?> {
    return dataStore.data.map { it[key] }
}