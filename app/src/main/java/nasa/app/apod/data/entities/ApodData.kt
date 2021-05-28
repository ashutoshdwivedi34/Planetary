package nasa.app.apod.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ApodData(
    @SerializedName("copyright")
    val copyright: String,
    @PrimaryKey
    @SerializedName("date")
    val date: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("hdurl")
    val hdurl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)