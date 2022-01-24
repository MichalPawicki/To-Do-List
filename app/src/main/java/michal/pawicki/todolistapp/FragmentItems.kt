package michal.pawicki.todolistapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import database.ToDoItemsDao
import kotlinx.coroutines.flow.collectLatest
import michal.pawicki.todolistapp.databinding.FragmentItemsBinding
import javax.inject.Inject


@AndroidEntryPoint
class FragmentItems : Fragment() {  //zmania Fragment() na ViewModels()
    @Inject
    lateinit var toDoItemsDao: ToDoItemsDao

    private var fragmentItems: FragmentItemsBinding? = null
    private val binding get() = fragmentItems!!
    private lateinit var adapters: ItemsAdapters
    private val viewModel: ItemsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapters = ItemsAdapters({
            id, status->
            toDoItemsDao.updateItemStatus(id, status)
        }, viewModel::openDetails,::showDeleteDialog)
    }
    // -------------------------------Ustawianie usunięcia z bazy danych --------------------------------------------
    private fun showDeleteDialog(id: Int) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Usuwanie zadania")
            .setMessage("Czy na pewno chcesz usunąć ten element?")
            .setPositiveButton("Usuń") { a, b -> toDoItemsDao.deleteItem(id) } // Podpiąć akcje pod dialog -2 linijki kodu?
            .setNegativeButton("Nie"){ a,b -> toDoItemsDao.observeAllItems()}
        dialog.show()
    }
    // --------------------------------------------------------------------------------------------------------------
    private fun navigateToAddItem(id: Int) {
        findNavController().navigate(FragmentItemsDirections.actionFragmentItemsToFragmentAddItem(id))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentItems = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }
// -------------------------------update do bazy danych --------------------------------------------

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.destination.collectLatest { handleNavigation(it) }
        }
        binding.itemsList.adapter = adapters
        toDoItemsDao.observeAllItems().observe(viewLifecycleOwner, {
            adapters.updateItems(it)
        })
        binding.addItemButton.setOnClickListener{
            viewModel.openNewItem()
            }

        }

    private fun handleNavigation(itemsDestination: ItemsDestination?) {
        when (itemsDestination){
            is ItemsDestination.Detail -> navigateToAddItem(itemsDestination.id)
            ItemsDestination.NewItem -> navigateToAddItem(0)
            null -> return
        }
    }
}



