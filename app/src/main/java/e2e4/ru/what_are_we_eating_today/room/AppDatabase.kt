package e2e4.ru.what_are_we_eating_today.room

import androidx.room.Database
import androidx.room.RoomDatabase
import e2e4.ru.what_are_we_eating_today.models.entity.FoodTypeEntity

@Database(entities = [FoodTypeEntity :: class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFoodWheelDao(): FoodWheelDao
}