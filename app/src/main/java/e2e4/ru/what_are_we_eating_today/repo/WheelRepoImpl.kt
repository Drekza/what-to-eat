package e2e4.ru.what_are_we_eating_today.repo

import e2e4.ru.what_are_we_eating_today.models.domain.Food
import e2e4.ru.what_are_we_eating_today.models.domain.FoodType
import e2e4.ru.what_are_we_eating_today.models.entity.FoodTypeEntity
import e2e4.ru.what_are_we_eating_today.room.FoodWheelDao

class WheelRepoImpl(
    private val foodWheelDao: FoodWheelDao,
) : WheelRepo {
    override suspend fun getFoodTypes(): List<FoodType> {
        return try {
            foodWheelDao.getFoodTypes().toDomain()
        } catch (ex: Exception) {
            emptyList()
        }
    }

    override fun insertFoodType(foodType: FoodType) {
        foodWheelDao.insertFoodType(foodType.toEntity())
    }

    override fun deleteAllFoodTypes() {
        foodWheelDao.deleteAllFoodTypes()
    }

    override suspend fun getFood(foodType: FoodType): Food {
        TODO("Not yet implemented")
    }

    private fun FoodType.toEntity(): FoodTypeEntity {
        return FoodTypeEntity(
            type = this.type
        )
    }

    private fun FoodTypeEntity.toDomain(): FoodType {
        return FoodType(this.type)
    }

    private fun List<FoodTypeEntity>.toDomain(): List<FoodType> {
        return this.map { it.toDomain() }
    }
}