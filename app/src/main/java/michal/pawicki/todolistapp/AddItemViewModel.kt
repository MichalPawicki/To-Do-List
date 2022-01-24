package michal.pawicki.todolistapp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import database.ToDoItemsDao
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(private val toDoItemsDao: ToDoItemsDao):  ViewModel() {

//    val itemId = // Mutablelivedadta - 2 livedate jedna obserwuje drugą
//    val item = toDoItemsDao.getItem(id)
}