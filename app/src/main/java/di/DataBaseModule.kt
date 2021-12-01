package di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import database.ToDoListDatabase

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Provides
    fun provideDataBase(@ApplicationContext applicationContext: Context): ToDoListDatabase =
        Room.databaseBuilder(
            applicationContext,
            ToDoListDatabase::class.java,
            "toDoItems"
        )
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideToDoItemsDao(db: ToDoListDatabase) = db.ToDoItemsDao()
}

// życie aktywności i fragmentów, mvvm, hilt, dagger2, livedata, coin, courtines, RXJava, jetpack compose - poczytaj o tym - naucz się, spróbuj dodać viewmodel + ustawić usunięcie