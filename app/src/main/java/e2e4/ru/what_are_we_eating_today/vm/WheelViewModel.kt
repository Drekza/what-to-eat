package e2e4.ru.what_are_we_eating_today.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import e2e4.ru.what_are_we_eating_today.models.domain.FoodType
import e2e4.ru.what_are_we_eating_today.repo.WheelRepo
import e2e4.ru.what_are_we_eating_today.utils.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WheelViewModel(
    private val wheelRepository: WheelRepo,
) : ViewModel() {
    private val _foodTypesList = MutableLiveData<List<FoodType>>()
    val foodTypesList = _foodTypesList.asLiveData()

    init {
        getFoodTypes()
    }

    private fun getFoodTypes() {
        viewModelScope.launch(Dispatchers.IO) {
            val foodTypes = wheelRepository.getFoodTypes()
            withContext(Dispatchers.Main){
                _foodTypesList.value = foodTypes
            }
        }
    }

    fun addFoodType(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val foodType = FoodType(type = type)
            wheelRepository.insertFoodType(foodType)
        }
    }

    fun clearFoodTypes() {
        viewModelScope.launch(Dispatchers.IO) {
            wheelRepository.deleteAllFoodTypes()
        }
    }

}