package michal.pawicki.todolistapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import michal.pawicki.todolistapp.data.ToDoItem
import michal.pawicki.todolistapp.data.ToDoItemsDao
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import michal.pawicki.todolistapp.domain.AplicationRepository
import javax.inject.Inject

// -------------------------------viewModel w fragmentItems --------------------------------------

@HiltViewModel
class ItemsViewModel @Inject constructor(private val repository: AplicationRepository): ViewModel() {
    private val _destination = Channel<ItemsDestination>()
    val destination = _destination.receiveAsFlow()
    private val _allItems= repository.observeAllItems().asLiveData()   //3 różne modele
    val allItems: LiveData<List<ToDoItem>> = _allItems // musimy dotrzeć do Livedata

    fun openDetails(id: Int){
        viewModelScope.launch {
            _destination.send(ItemsDestination.Detail(id))
        }

    }
    fun openNewItem(){
        viewModelScope.launch { _destination.send(ItemsDestination.NewItem) }
    }

    fun uptadeItemStatus(id: Int, status: Boolean) {
        repository.updateItemStatus(id, status)
    }

    fun deleteItem(id: Int) {
        repository.deleteItem(id)
    }
}
