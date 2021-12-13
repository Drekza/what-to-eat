package e2e4.ru.what_are_we_eating_today.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import e2e4.ru.what_are_we_eating_today.models.entity.FoodTypeEntity

@Dao
interface FoodWheelDao {
    @Query("SELECT * FROM foodTypes")
    fun getFoodTypes(): List<FoodTypeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFoodType(foodType: FoodTypeEntity)

    @Query("DELETE FROM foodTypes")
    fun deleteAllFoodTypes()
}