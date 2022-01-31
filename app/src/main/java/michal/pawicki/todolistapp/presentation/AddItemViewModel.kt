package michal.pawicki.todolistapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import michal.pawicki.todolistapp.data.ToDoItem
import michal.pawicki.todolistapp.data.ToDoItemsDao
import kotlinx.coroutines.launch
import javax.inject.Inject

// -------------------------------viewModel w fragmentAddItem --------------------------------------

@HiltViewModel
class AddItemViewModel @Inject constructor(private val toDoItemsDao: ToDoItemsDao):  ViewModel() {

    private val itemPublisher = MutableLiveData<ToDoItem>()
    val item: LiveData<ToDoItem> = itemPublisher

    fun setId (id: Int) {
        itemPublisher.value = toDoItemsDao.getItemSimply(id)
    }

    fun addItemPressed(newItem: ToDoItem) {
        viewModelScope.launch {
            if (newItem.id == 0) {
                toDoItemsDao.addItem(newItem)
            } else {
                toDoItemsDao.updateItem(newItem)
            }
        }

    }
}

// Zasady SOLID