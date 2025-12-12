package es.lasalle.m07.data.repository

import android.util.Log
import es.lasalle.m07.data.local.RocketDao
import es.lasalle.m07.data.local.RocketEntity
import es.lasalle.m07.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class RocketsRepository(private val rocketDao: RocketDao) {

    private val retrofitInstance = RetrofitInstance()

    val rockets: Flow<List<RocketEntity>> = rocketDao.getRockets()

    suspend fun refreshRockets() {
        return try {
            val localRockets = retrofitInstance.api.getRockets()
            rocketDao.deleteAllRockets()
            rocketDao.insertAllRockets(localRockets)
        } catch (e: Exception) {
            Log.e("RocketsRepository", "Error al refrescar los cohetes: ${e.message}")
            throw e
        }
    }
}