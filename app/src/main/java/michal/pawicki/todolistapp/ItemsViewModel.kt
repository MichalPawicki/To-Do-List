package michal.pawicki.todolistapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ItemsViewModel: ViewModel() {
    private val _destination = Channel<ItemsDestination>()
    val destination = _destination.receiveAsFlow()

    fun openDetails(id: Int){
        viewModelScope.launch {
            _destination.send(ItemsDestination.Detail(id))
        }

    }
    fun openNewItem(){
        viewModelScope.launch { _destination.send(ItemsDestination.NewItem) }
    }
}
