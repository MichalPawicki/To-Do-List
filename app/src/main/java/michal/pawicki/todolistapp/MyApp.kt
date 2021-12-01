package michal.pawicki.todolistapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import database.ToDoItemsDao
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {
    @Inject
    lateinit var toDoItemsDao: ToDoItemsDao
}