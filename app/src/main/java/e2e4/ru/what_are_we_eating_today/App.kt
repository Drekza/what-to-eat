package e2e4.ru.what_are_we_eating_today

import android.app.Application
import e2e4.ru.what_are_we_eating_today.di.wheelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(wheelModule)
        }
    }
}