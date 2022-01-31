package michal.pawicki.todolistapp.domain

import java.util.*


data class ToDoItemDomain(
    val id: Int,
    val title: String,
    val note: String,
    val date: Date,
    val status: Boolean
)