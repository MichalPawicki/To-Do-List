package michal.pawicki.todolistapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel: ViewModel() {
    val navigateLiveData = MutableLiveData <Boolean>(false)
    fun navigateNext() {
        navigateLiveData.value = true
    }
}