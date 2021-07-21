package `in`.stvayush.contextualcards.views.activities

import `in`.stvayush.contextualcards.R
import `in`.stvayush.contextualcards.viewmodels.CardGroupViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val cardGroupViewModel: CardGroupViewModel by lazy {
        ViewModelProvider(this).get(CardGroupViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hit the endpoint
        cardGroupViewModel.fetchCards()
    }

    // TODO: 21/7/21 Render data on screen

}
