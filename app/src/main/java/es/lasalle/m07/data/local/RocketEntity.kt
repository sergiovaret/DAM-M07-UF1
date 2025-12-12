package es.lasalle.m07.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "rockets")

data class RocketEntity(
    @PrimaryKey val id: String,
    val name: String,
    val active: Boolean?,
    val flickr_images: List<String>?,
    val country: String?,
    val first_flight: String?,
    val success_rate_pct: Int?,
    val cost_per_launch: Long?,
    val stages: Int?,
    val description: String?,
    val wikipedia: String?
)