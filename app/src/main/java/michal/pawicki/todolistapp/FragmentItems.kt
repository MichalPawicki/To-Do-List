package michal.pawicki.todolistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import michal.pawicki.todolistapp.databinding.FragmentItemsBinding

class FragmentItems : Fragment() {

    private var fragmentItems: FragmentItemsBinding? = null
    private val binding get() = fragmentItems!!
    private lateinit var adapters: ItemsAdapters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapters = ItemsAdapters{
            id, status->
            itemsDao().updateItemStatus(id, status)
        }
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
        itemsDao().observeAllItems().observe(viewLifecycleOwner, {
            adapters.updateItems(it)
        })
        binding.addItemButton.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentItems_to_fragmentAddItem)
            }

        }
    }

// Poczytaj o adapterach, bazach danych - wybor godzin, wyswietlanie dialogu itp.