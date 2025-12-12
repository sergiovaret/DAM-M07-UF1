package es.lasalle.m07

import android.app.Application
import es.lasalle.m07.data.local.RocketsDatabase
import es.lasalle.m07.data.repository.RocketsRepository

class MyApplication : Application() {


    val database by lazy {
        val db = RocketsDatabase.getDatabase(this)
        db
    }
    val repository by lazy {
        val repo = RocketsRepository(database.rocketDao())
        repo
    }
}