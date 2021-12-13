package e2e4.ru.what_are_we_eating_today.di

import android.content.Context
import androidx.room.Room
import e2e4.ru.what_are_we_eating_today.R
import e2e4.ru.what_are_we_eating_today.repo.WheelRepo
import e2e4.ru.what_are_we_eating_today.repo.WheelRepoImpl
import e2e4.ru.what_are_we_eating_today.room.AppDatabase
import e2e4.ru.what_are_we_eating_today.room.FoodWheelDao
import e2e4.ru.what_are_we_eating_today.vm.WheelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wheelModule = module {
    single { provideDatabase(context = get()) }
    single { provideSearchHistoryDao(appDB = get()) }
    single<WheelRepo> { WheelRepoImpl(foodWheelDao = get()) }
    viewModel { WheelViewModel(wheelRepository = get()) }
}

private fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        context.getString(R.string.foodDb)
    ).build()
}

private fun provideSearchHistoryDao(appDB: AppDatabase): FoodWheelDao = appDB.getFoodWheelDao()