package michal.pawicki.todolistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import michal.pawicki.todolistapp.databinding.FragmentItemsBinding

class FragmentItems : Fragment() {

    private var fragmentItems: FragmentItemsBinding? = null
    private val binding get() = fragmentItems!!

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
        binding.addItemButton.setOnClickListener{
            findNavController().navigate(R.id.action_fragmentItems_to_fragmentAddItem)
        }

        }
    }