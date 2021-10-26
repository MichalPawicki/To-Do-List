package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoItem::class], version = 1)
abstract class ToDoListDatabase: RoomDatabase() {
    abstract fun ToDoItemsDao(): ToDoItemsDao

    abstract val applicationContext: Context
    val db = Room.databaseBuilder(
        applicationContext,
        ToDoListDatabase::class.java, 
        "toDoItems"
    ).build()

    val toDoItemsDao = db.ToDoItemsDao()
    val users: List<ToDoItem> = toDoItemsDao.getAllItems()
}