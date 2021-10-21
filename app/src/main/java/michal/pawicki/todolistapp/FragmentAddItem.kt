package michal.pawicki.todolistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import michal.pawicki.todolistapp.databinding.FragmentAddItemBinding

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
            findNavController().navigate(R.id.action_fragmentAddItem_to_fragmentItemItem)
        }

    }
}