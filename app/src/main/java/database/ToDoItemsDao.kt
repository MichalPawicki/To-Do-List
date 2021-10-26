package database

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
    @Query( "select * from toDoItems where id=:id limit 1") //zwraca jeden element
    fun getItem(id: Int) : ToDoItem
    @Update
    fun updateItem(toDoItem: ToDoItem)
}

//brak jedneo elementu - dorobić abstrakcyjna klasa