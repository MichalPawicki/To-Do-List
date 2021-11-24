package database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoItemsDao {
    @Insert
    fun addItem(toDoItem: ToDoItem)
    @Delete
    fun deleteItem(toDoItem: ToDoItem)
    @Query("delete from toDoItems where id=:id")
    fun deleteItem(id: Int)
    @Query("select * from toDoItems")
    fun getAllItems() : List<ToDoItem>
    @Query("select * from toDoItems")
    fun observeAllItems() : LiveData<List<ToDoItem>>
    @Query( "select * from toDoItems where id=:id limit 1") //zwraca jeden element
    fun getItem(id: Int) : LiveData<ToDoItem>
    @Update
    fun updateItem(toDoItem: ToDoItem)
    @Query("update toDoItems set status = :status where id = :id")
    fun updateItemStatus(id: Int, status: Boolean)
}

