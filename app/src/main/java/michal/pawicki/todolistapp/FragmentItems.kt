package michal.pawicki.todolistapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import database.ToDoItemsDao
import michal.pawicki.todolistapp.databinding.FragmentItemsBinding
import javax.inject.Inject

@AndroidEntryPoint
class FragmentItems : Fragment() {
    @Inject
    lateinit var toDoItemsDao: ToDoItemsDao

    private var fragmentItems: FragmentItemsBinding? = null
    private val binding get() = fragmentItems!!
    private lateinit var adapters: ItemsAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapters = ItemsAdapters({
            id, status->
            toDoItemsDao.updateItemStatus(id, status)
        },::navigateToAddItem,::showDeleteDialog)
    }

    private fun showDeleteDialog(id: Int) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Usuwanie zadania")
            .setMessage("Czy na pewno chcesz usunąć ten element?")
            .setPositiveButton("Usuń") { a, b -> toDoItemsDao.deleteItem(id) } // Podpiąć akcje pod dialog -2 linijki kodu?
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemsList.adapter = adapters
        toDoItemsDao.observeAllItems().observe(viewLifecycleOwner, {
            adapters.updateItems(it)
        })
        binding.addItemButton.setOnClickListener{
            navigateToAddItem(id = 0)
            }

        }
    }


