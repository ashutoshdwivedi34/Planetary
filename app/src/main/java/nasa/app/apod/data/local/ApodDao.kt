package nasa.app.apod.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import nasa.app.apod.data.entities.ApodData

@Dao
interface ApodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(apodData: ApodData)


    @Query("SELECT * FROM apoddata where  DATE(date)  = DATE('now') ")
    fun getLastInsertedApodData(): LiveData<ApodData>

}