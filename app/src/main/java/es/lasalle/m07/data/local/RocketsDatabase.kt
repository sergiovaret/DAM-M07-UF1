package es.lasalle.m07.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
@Database(
    entities = [
        RocketEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RocketsDatabase : RoomDatabase() {

    abstract fun rocketDao(): RocketDao

    companion object {
        private const val DATABASE_NAME = "rockets_database"
        @Volatile
        private var INSTANCE: RocketsDatabase? = null

        fun getDatabase(context: Context): RocketsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RocketsDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}