package michal.pawicki.todolistapp.domain

import kotlinx.coroutines.flow.Flow


interface AplicationRepository {

    suspend fun addItem(toDoItem: ToDoItemDomain)

    fun deleteItem(toDoItem: ToDoItemDomain)

    fun deleteItem(id: Int)

    suspend fun getAllItems() : List<ToDoItemDomain>

    fun observeAllItems() : Flow<List<ToDoItemDomain>>

    fun getItem(id: Int) : Flow<ToDoItemDomain>

    suspend fun updateItem(toDoItem: ToDoItemDomain)

    fun updateItemStatus(id: Int, status: Boolean)

    fun getItemSimply(id: Int) : ToDoItemDomain
}

