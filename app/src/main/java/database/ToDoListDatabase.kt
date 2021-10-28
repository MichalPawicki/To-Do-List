package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ToDoItem::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class ToDoListDatabase: RoomDatabase() {
    abstract fun ToDoItemsDao(): ToDoItemsDao

}