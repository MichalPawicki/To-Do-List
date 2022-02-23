package michal.pawicki.todolistapp.presentation

import java.util.*


data class ToDoItemUi(
    val id: Int,
    val title: String,
    val note: String,
    val dateTxt: String,
    val date: Date,
    val status: Boolean
)