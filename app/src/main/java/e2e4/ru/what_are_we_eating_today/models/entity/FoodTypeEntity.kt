package e2e4.ru.what_are_we_eating_today.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foodTypes")
data class FoodTypeEntity(

    @PrimaryKey
    @ColumnInfo(name = "type")
    val type: String,
)