package michal.pawicki.todolistapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import database.ToDoItem
import database.ToDoItemsDao
import javax.inject.Inject

// -------------------------------viewModel w fragmentAddItem --------------------------------------

@HiltViewModel
class AddItemViewModel @Inject constructor(private val toDoItemsDao: ToDoItemsDao):  ViewModel() {

    val itemId: Any = MutableLiveData<Any>()
    val item: MutableLiveData<List<ToDoItem>> = toDoItemsDao.observeAllItems() as MutableLiveData<List<ToDoItem>>

//    val itemId = // Mutablelivedadta - 2 livedate jedna obserwuje drugą
//    val item = toDoItemsDao.getItem(id)
    fun updateItem() {
        toDoItemsDao.updateItem(item)
    }

    suspend fun addItem(item: ToDoItem) {
        toDoItemsDao.addItem(item)
    }
}