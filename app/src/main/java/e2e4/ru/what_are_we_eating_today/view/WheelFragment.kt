package e2e4.ru.what_are_we_eating_today.view

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bluehomestudio.luckywheel.WheelItem
import e2e4.ru.what_are_we_eating_today.R
import e2e4.ru.what_are_we_eating_today.databinding.FragmentWheelBinding
import e2e4.ru.what_are_we_eating_today.utils.autoCleared
import e2e4.ru.what_are_we_eating_today.vm.WheelViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class WheelFragment : Fragment(R.layout.fragment_wheel) {
    private val viewModel: WheelViewModel by viewModel()
    private var binding: FragmentWheelBinding by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentWheelBinding.bind(view)

        viewModel.clearFoodTypes()

        setUpObserve()
        setUpUi()
    }

    private fun setUpObserve() {
        viewModel.foodTypesList.observe(viewLifecycleOwner) { foodTypes ->
            val wheelItems = mutableListOf<WheelItem>()
            foodTypes.forEachIndexed { index, foodType ->
                wheelItems.add(
                    WheelItem(
                        R.color.colorPrimary,
                        BitmapFactory.decodeResource(context?.resources, R.drawable.ic_logo),
                        foodType.type
                    )
                )
            }
            if (wheelItems.isNotEmpty()) {
                binding.luckyWheel.addWheelItems(wheelItems)
                val targetItem = Random.nextInt(0, wheelItems.size)
                binding.luckyWheel.setTarget(targetItem)
                binding.luckyWheel.isVisible = true
            }
        }
    }

    private fun setUpUi() {
        with(binding) {
            btnAddItem.setOnClickListener {

            }
        }
    }
}