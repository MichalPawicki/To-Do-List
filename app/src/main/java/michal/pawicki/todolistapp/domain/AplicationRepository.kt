package michal.pawicki.todolistapp.domain

import androidx.lifecycle.LiveData


interface AplicationRepository {

    suspend fun addItem(toDoItem: ToDoItemDomain)

    fun deleteItem(toDoItem: ToDoItemDomain)

    fun deleteItem(id: Int)

    suspend fun getAllItems() : List<ToDoItemDomain>

    fun observeAllItems() : LiveData<List<ToDoItemDomain>>

    fun getItem(id: Int) : LiveData<ToDoItemDomain>

    suspend fun updateItem(toDoItem: ToDoItemDomain)

    fun updateItemStatus(id: Int, status: Boolean)

    fun getItemSimply(id: Int) : ToDoItemDomain
}

