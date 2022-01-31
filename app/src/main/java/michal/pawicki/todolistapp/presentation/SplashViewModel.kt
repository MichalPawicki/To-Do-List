package michal.pawicki.todolistapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// -------------------------------viewModel w fragmentSplash --------------------------------------

class SplashViewModel: ViewModel() {
    val navigateLiveData = MutableLiveData <Boolean>(false)
    fun navigateNext() {
        navigateLiveData.value = true
    }
}