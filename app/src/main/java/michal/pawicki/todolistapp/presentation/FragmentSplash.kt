package michal.pawicki.todolistapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import michal.pawicki.todolistapp.R
import michal.pawicki.todolistapp.databinding.FragmentSplashBinding

// -------------------Budowa widoku w FragmentSplash  ----------------------------------------------
class FragmentSplash : Fragment() {

    private var fragmentSplash: FragmentSplashBinding? = null
    private val binding get() = fragmentSplash!!
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSplash = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    // -------------------------------Przejście FragmentSplash do FragmentItems  -------------------
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigateLiveData.observe(
            viewLifecycleOwner,
            ::handleNavigation // <- Referencja do funkcji
        )
        // -------------------Nasłuchiwanie na kliknięcie: włącza funkcje navigateNext() -----------
        binding.startText.setOnClickListener {
            viewModel.navigateNext()
        }

    }

    // -------------------Funkcja w której przechodzi się z FragmentSplash do FragmentItems---------
    private fun handleNavigation(goNext: Boolean) {
        if (goNext) {
            findNavController().navigate(R.id.action_fragmentSplash_to_fragmentItems)
        }
    }

}