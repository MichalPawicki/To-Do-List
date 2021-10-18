package michal.pawicki.todolistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import michal.pawicki.todolistapp.databinding.FragmentSplashBinding

class FragmentAddItem: Fragment() {

    private var fragmentAddItem: FragmentSplashBinding? = null
    private val binding get() = fragmentAddItem!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentAddItem = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentAddItem = null
    }
}