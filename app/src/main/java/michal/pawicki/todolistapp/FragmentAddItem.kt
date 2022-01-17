package michal.pawicki.todolistapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import database.ToDoItem
import database.ToDoItemsDao
import michal.pawicki.todolistapp.databinding.FragmentAddItemBinding
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class FragmentAddItem: Fragment() {
    @Inject
    lateinit var toDoItemsDao: ToDoItemsDao

    private var cal = Calendar.getInstance()
    private var fragmentAddItem: FragmentAddItemBinding? = null
    private val binding get() = fragmentAddItem!!
    private var itemId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: FragmentAddItemArgs by navArgs()
         itemId = args.id
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentAddItem = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }
// -------------------------------Ustawianie kalendarza --------------------------------------------
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener {
            addItemToDb()
        }
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.selectDataButton.text = dateFormat.format(cal.time)
            }
        binding.selectDataButton.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        if (itemId > 0 ) {
            toDoItemsDao.getItem(itemId).observe(viewLifecycleOwner, ::fillItem)
        }

  }

    private fun fillItem(toDoItem: ToDoItem) {
        binding.addContentTxt.setText(toDoItem.title)
        binding.addCategoryTxt.setText(toDoItem.note)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        cal.time = toDoItem.date
        binding.selectDataButton.text = dateFormat.format(cal.time)
    }

    private fun addItemToDb() {
        val title = binding.addCategoryTxt.text.toString()
        val content = binding.addContentTxt.text.toString()
        val date = cal.time
        val item =  ToDoItem(itemId, title, content, date, status = false)
        if (itemId == 0 ) {
            toDoItemsDao.addItem(item)
        }
        else{
            toDoItemsDao.updateItem(item)
        }
        findNavController().popBackStack()
    }
}