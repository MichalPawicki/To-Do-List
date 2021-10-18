package michal.pawicki.todolistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import michal.pawicki.todolistapp.databinding.FragmentSplashBinding

class FragmentSplash : Fragment() {

    private var fragmentSplash: FragmentSplashBinding? = null
    private val binding get() = fragmentSplash!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSplash = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startText.setOnClickListener {
            binding.startText.text = "done"
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentSplash = null
    }
}