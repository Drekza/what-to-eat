package e2e4.ru.what_are_we_eating_today.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Ленивый делагат, присваивающий своему свойству значение null при вызове onDestroyView() фрагмента
 * @throws IllegalStateException при обращении к переменной в onDestroyView() или до инициализации
 */
class AutoClearedValue<T : Any>(fragment: Fragment) : ReadWriteProperty<Fragment, T> {
    private var _value: T? = null

    init {
        // когда viewLifecycle становится доступен,
        // цепляет на него лисенер, обнуляющий value на onDestroyView
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
            viewLifecycleOwner?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    _value = null
                }
            })
        }
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        _value = value
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException(
            "Attempted to call AutoClearedValue after OnDestroyView() or before initialization"
        )
    }
}

/**
 * Инициализирует свойство как [AutoClearedValue]
 */
fun <T : Any> Fragment.autoCleared() = AutoClearedValue<T>(this)
