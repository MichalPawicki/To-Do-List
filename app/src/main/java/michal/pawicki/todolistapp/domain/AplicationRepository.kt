package michal.pawicki.todolistapp.domain

import kotlinx.coroutines.flow.Flow


interface AplicationRepository {

    suspend fun addItem(toDoItem: ToDoItemDomain)

    suspend fun deleteItem(toDoItem: ToDoItemDomain)

    suspend fun deleteItem(id: Int)

    suspend fun getAllItems() : List<ToDoItemDomain>

    fun observeAllItems() : Flow<List<ToDoItemDomain>>

    fun getItem(id: Int) : Flow<ToDoItemDomain>

    suspend fun updateItem(toDoItem: ToDoItemDomain)

    suspend fun updateItemStatus(id: Int, status: Boolean)

    suspend fun getItemSimply(id: Int) : ToDoItemDomain?
}

