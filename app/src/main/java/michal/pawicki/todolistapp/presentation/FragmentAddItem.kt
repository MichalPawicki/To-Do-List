package michal.pawicki.todolistapp.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import michal.pawicki.todolistapp.data.ToDoItem
import michal.pawicki.todolistapp.databinding.FragmentAddItemBinding
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class FragmentAddItem : Fragment() {

    private var cal = Calendar.getInstance()
    private var fragmentAddItem: FragmentAddItemBinding? = null
    private val binding get() = fragmentAddItem!!
    private var itemId: Int = 0
    private val viewModel: AddItemViewModel by viewModels()


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
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
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
        viewModel.item.observe(viewLifecycleOwner, ::fillItem)
        viewModel.setId(itemId)

    }

    private fun fillItem(toDoItem: ToDoItem) {
        binding.addCategoryTxt.setText(toDoItem.title)
        binding.addContentTxt.setText(toDoItem.note)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        cal.time = toDoItem.date
        binding.selectDataButton.text = dateFormat.format(cal.time)
    }

    private fun addItemToDb() {
        val title = binding.addCategoryTxt.text.toString()
        val content = binding.addContentTxt.text.toString()
        val date = cal.time
        val item = ToDoItem(itemId, title, content, date, status = false)
        viewModel.addItemPressed(item)
        findNavController().popBackStack()

    }
}







