package michal.pawicki.todolistapp

sealed class ItemsDestination {   //Typ wyliczeniowy (Enum na "sterydach")
    data class Detail(val id: Int): ItemsDestination()
    object NewItem : ItemsDestination()
}
// Poczytac o courtines , sealed class , objektach