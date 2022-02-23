package michal.pawicki.todolistapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoItemsDao {
    @Insert
    suspend fun addItem(toDoItem: ToDoItem)
    @Delete
    suspend fun deleteItem(toDoItem: ToDoItem)
    @Query("delete from toDoItems where id=:id")
    suspend fun deleteItem(id: Int)
    @Query("select * from toDoItems")
    suspend fun getAllItems() : List<ToDoItem>
    @Query("select * from toDoItems")
    fun observeAllItems() : Flow<List<ToDoItem>>
    @Query( "select * from toDoItems where id=:id limit 1") //zwraca jeden element
    fun getItem(id: Int) : Flow<ToDoItem>
    @Update
    suspend fun updateItem(toDoItem: ToDoItem)
    @Query("update toDoItems set status = :status where id = :id")
    suspend fun updateItemStatus(id: Int, status: Boolean)
    @Query( "select * from toDoItems where id=:id limit 1") //zwraca jeden element
    suspend fun getItemSimply(id: Int) : ToDoItem?
}

