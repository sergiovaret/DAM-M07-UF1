package es.lasalle.m07.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRockets(rockets: List<RocketEntity>)

    @Query("SELECT * FROM rockets")
    fun getRockets(): Flow<List<RocketEntity>>

    @Query("DELETE FROM rockets")
    suspend fun deleteAllRockets()

}