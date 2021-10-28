package michal.pawicki.todolistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import database.ToDoItem
import michal.pawicki.todolistapp.databinding.FragmentAddItemBinding
import java.util.*

class FragmentAddItem: Fragment() {

    private var fragmentAddItem: FragmentAddItemBinding? = null
    private val binding get() = fragmentAddItem!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentAddItem = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener {
            addItemToDb()
        }

    }

    private fun addItemToDb() {
        val title = binding.addCategoryTxt.text.toString()
        val content = binding.addContentTxt.text.toString()
        val date = Date()
        val item =  ToDoItem(0, title, content, date, status = false)
        itemsDao().addItem(item)
        findNavController().popBackStack()
    }
}