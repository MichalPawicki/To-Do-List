package michal.pawicki.todolistapp
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.room.Room
import database.ToDoItemsDao
import database.ToDoListDatabase

class MyApp : Application() {
    lateinit var db : ToDoListDatabase
    lateinit var toDoItemsDao : ToDoItemsDao
    override fun onCreate() {
        super.onCreate()

            db = Room.databaseBuilder(
            applicationContext,
            ToDoListDatabase::class.java,
            "toDoItems"
        )
                .allowMainThreadQueries()
                .build()
        toDoItemsDao = db.ToDoItemsDao()

    }
}


fun Fragment.itemsDao() : ToDoItemsDao{
    return (requireActivity().applicationContext as MyApp).toDoItemsDao  // mamy dostęp do Dao w każdym fragmencie
}