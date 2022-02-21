package michal.pawicki.todolistapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import michal.pawicki.todolistapp.domain.AplicationRepository
import michal.pawicki.todolistapp.domain.ToDoItemDomain
import javax.inject.Inject

class AplicationRepositoryImpl @Inject constructor(
    private val dao: ToDoItemsDao
) : AplicationRepository {
    override suspend fun addItem(toDoItem: ToDoItemDomain) {
        dao.addItem(
            toDoItem.toDB()
        )
    }

    override fun deleteItem(toDoItem: ToDoItemDomain) {
        dao.deleteItem(
            toDoItem.toDB()
        )
    }

    override fun deleteItem(id: Int) {
        dao.deleteItem(id)
    }

    override suspend fun getAllItems(): List<ToDoItemDomain> {
        return dao.getAllItems().map { toDoItem ->
            toDoItem.toDomain()
        }
    }

    override fun observeAllItems(): Flow<List<ToDoItemDomain>> {
        return dao.observeAllItems().map { items ->
            items.toDomainItems()

        }
    }

    override fun getItem(id: Int): Flow<ToDoItemDomain> {
        return dao.getItem(id).map { item -> item.toDomain() }
    }

    override suspend fun updateItem(toDoItem: ToDoItemDomain) {
        dao.updateItem(
            toDoItem.toDB()
        )
    }

    override fun updateItemStatus(id: Int, status: Boolean) {
        dao.updateItemStatus(id, status)
    }

    override fun getItemSimply(id: Int): ToDoItemDomain {
        return dao.getItemSimply(id).toDomain()
    }

    private fun ToDoItemDomain.toDB() = ToDoItem(
        id, title, note, date, status
    )

    private fun ToDoItem.toDomain() = ToDoItemDomain(
        id, title, note, date, status
    )

    private fun List<ToDoItem>.toDomainItems() = map { item -> item.toDomain() }
}