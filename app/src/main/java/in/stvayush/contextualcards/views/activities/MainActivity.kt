package `in`.stvayush.contextualcards.views.activities

import `in`.stvayush.contextualcards.databinding.ActivityMainBinding
import `in`.stvayush.contextualcards.viewmodels.CardGroupViewModel
import `in`.stvayush.contextualcards.views.adapters.CardGroupsAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private val cardGroupViewModel: CardGroupViewModel by lazy {
        ViewModelProvider(this).get(CardGroupViewModel::class.java)
    }

    private lateinit var cardGroupsAdapter: CardGroupsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        cardGroupsAdapter = CardGroupsAdapter(this)
        setContentView(activityMainBinding.root)
        activityMainBinding.rvRoot.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cardGroupsAdapter
        }
        // Hit the endpoint
        cardGroupViewModel.fetchCards()
        setData()
    }

    private fun setData() {
        cardGroupViewModel.fetchSuccessful.observe(this, {
            it?.let {
                if (it)
                    cardGroupsAdapter.setCardGroupData(cardGroupViewModel.cardGroup)
            }
        })
    }
    // TODO: 21/7/21 Render data on screen

}
