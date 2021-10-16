package michal.pawicki.todolistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import michal.pawicki.todolistapp.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

//Podpiąć fragmenty, nawigacje
//poczytać o android room