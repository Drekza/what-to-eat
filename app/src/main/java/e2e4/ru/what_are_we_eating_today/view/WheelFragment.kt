package e2e4.ru.what_are_we_eating_today.view

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bluehomestudio.luckywheel.WheelItem
import e2e4.ru.what_are_we_eating_today.R
import e2e4.ru.what_are_we_eating_today.databinding.FragmentWheelBinding
import e2e4.ru.what_are_we_eating_today.utils.autoCleared
import e2e4.ru.what_are_we_eating_today.vm.WheelViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WheelFragment : Fragment(R.layout.fragment_wheel) {
    private val viewModel: WheelViewModel by viewModel()
    private var binding: FragmentWheelBinding by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentWheelBinding.bind(view)

        viewModel.clearFoodTypes()

        viewModel.addFoodType("Доставка")
        viewModel.addFoodType("Сходить за едой")

        setUpObserve()
    }

    private fun setUpObserve() {
        viewModel.foodTypesList.observe(viewLifecycleOwner) { foodTypes ->
            val wheelItems = mutableListOf<WheelItem>()
            foodTypes.forEach {
                wheelItems.add(
                    WheelItem(
                        R.color.colorPrimary,
                        BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_fastfood_24),
                        it.type
                    )
                )
            }
            if (wheelItems.isNotEmpty()) {
                binding.luckyWheel.addWheelItems(wheelItems)
            }
        }
    }
}