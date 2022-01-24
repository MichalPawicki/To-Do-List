package michal.pawicki.todolistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import database.ToDoItem
import database.ToDoItemsDao
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(private val toDoItemsDao: ToDoItemsDao): ViewModel() {
    private val _destination = Channel<ItemsDestination>()
    val destination = _destination.receiveAsFlow()
    val allItems: LiveData<List<ToDoItem>> = toDoItemsDao.observeAllItems()

    fun openDetails(id: Int){
        viewModelScope.launch {
            _destination.send(ItemsDestination.Detail(id))
        }

    }
    fun openNewItem(){
        viewModelScope.launch { _destination.send(ItemsDestination.NewItem) }
    }

    fun uptadeItemStatus(id: Int, status: Boolean) {
        toDoItemsDao.updateItemStatus(id, status)
    }

    fun deleteItem(id: Int) {
        toDoItemsDao.deleteItem(id)
    }
}
