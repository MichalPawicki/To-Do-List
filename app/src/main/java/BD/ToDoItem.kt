package BD

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "toDoItems")
data class  ToDoItem(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title: String,
    val note: String,
    val date: Date,
    val status: Boolean
)