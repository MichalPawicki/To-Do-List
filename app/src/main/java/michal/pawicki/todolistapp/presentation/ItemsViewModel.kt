package michal.pawicki.todolistapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import michal.pawicki.todolistapp.domain.AplicationRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

// -------------------------------viewModel w fragmentItems --------------------------------------

@HiltViewModel
class ItemsViewModel @Inject constructor(private val repository: AplicationRepository): ViewModel() {
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val _destination = Channel<ItemsDestination>()
    val destination = _destination.receiveAsFlow()
    private val _allItems= repository.observeAllItems().map{
        it.map { item -> ToDoItemUi(item.id, item.title, item.note, dateFormat.format(item.date), item.date, item.status) }
    }.asLiveData()
    val allItems: LiveData<List<ToDoItemUi>> = _allItems

    fun openDetails(id: Int){
        viewModelScope.launch {
            _destination.send(ItemsDestination.Detail(id))
        }

    }
    fun openNewItem(){
        viewModelScope.launch { _destination.send(ItemsDestination.NewItem) }
    }

    fun uptadeItemStatus(id: Int, status: Boolean) {
        viewModelScope.launch{repository.updateItemStatus(id, status)}
    }

    fun deleteItem(id: Int) {
        viewModelScope.launch{repository.deleteItem(id)}
    }
}
