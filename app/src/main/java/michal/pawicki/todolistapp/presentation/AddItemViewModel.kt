package michal.pawicki.todolistapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import michal.pawicki.todolistapp.domain.AplicationRepository
import michal.pawicki.todolistapp.domain.ToDoItemDomain
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

// -------------------------------viewModel w fragmentAddItem --------------------------------------

@HiltViewModel
class AddItemViewModel @Inject constructor(private val repository: AplicationRepository):  ViewModel() {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val itemPublisher = MutableLiveData<ToDoItemUi>()
    val item: LiveData<ToDoItemUi> = itemPublisher
    private var id: Int = 0

    fun setId (id: Int) {
        this.id = id
        viewModelScope.launch{
            val item = repository.getItemSimply(id) ?: return@launch
            val itemUi = ToDoItemUi(item.id, item.title, item.note, dateFormat.format(item.date), item.date, item.status)
            itemPublisher.value = itemUi
        }
    }

    fun addItemPressed(title: String, content: String, date: Date) {
        viewModelScope.launch {
            val itemDomain = ToDoItemDomain(id, title, content, date, status = false)
            if (id == 0) {
                repository.addItem(itemDomain)
            } else {
                repository.updateItem(itemDomain)
            }
        }

    }
}

// Zasady SOLID, poucz się!