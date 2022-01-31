package michal.pawicki.todolistapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import michal.pawicki.todolistapp.data.ToDoItemsDao
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {
    @Inject
    lateinit var toDoItemsDao: ToDoItemsDao
}