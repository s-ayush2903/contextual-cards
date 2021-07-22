package `in`.stvayush.contextualcards.views.activities

import `in`.stvayush.contextualcards.databinding.ActivityMainBinding
import `in`.stvayush.contextualcards.models.CardGroup
import `in`.stvayush.contextualcards.viewmodels.CardGroupViewModel
import `in`.stvayush.contextualcards.views.adapters.CardGroupsAdapter
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val TAG = "MainActivity"
    private val cardGroupViewModel: CardGroupViewModel by lazy {
        ViewModelProvider(this).get(CardGroupViewModel::class.java)
    }

    private lateinit var cardGroupsAdapter: CardGroupsAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        cardGroupsAdapter = CardGroupsAdapter(this)
        setContentView(activityMainBinding.root)

        with(activityMainBinding) {
            rvRoot.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = cardGroupsAdapter
            }
            rootSwipeRefreshLayout.setOnRefreshListener(this@MainActivity)
        }

        // Hit the endpoint
        fetchData()
    }

    override fun onResume() {
        super.onResume()
        onRefresh()
    }

    override fun onRefresh() {
        displayLoadingScreen()
        cardGroupViewModel.fetchCards()
    }

    private fun fetchData() {
        cardGroupViewModel.fetchSuccessful.observe(this, {
            it?.let {
                if (it) {
                    renderData(cardGroupViewModel.cardGroup)
                } else {
                    displayErrorScreen(cardGroupViewModel.errorMessage)
                }
            }
        })
    }

    private fun renderData(cardGroups: List<CardGroup>) {
        Log.d(TAG, "renderData: Rendering data....")
        with(activityMainBinding) {
            rootSwipeRefreshLayout.isRefreshing = false
            errorLayout.noConnectionLayout.visibility = GONE
            shimmerRecyclerViewLayout.visibility = GONE
            rvRoot.visibility = VISIBLE
            cardGroupsAdapter.setCardGroupData(cardGroups as ArrayList<CardGroup>)
        }
    }

    private fun displayErrorScreen(errorMessage: String) {
        with(activityMainBinding) {
            rootSwipeRefreshLayout.isRefreshing = false
            shimmerRecyclerViewLayout.visibility = GONE
            errorLayout.noConnectionLayout.visibility = VISIBLE
            errorLayout.errorMessage.text = errorMessage
        }
    }

    private fun displayLoadingScreen() {
        with(activityMainBinding) {
            rootSwipeRefreshLayout.isRefreshing = false
            errorLayout.noConnectionLayout.visibility = GONE
            rvRoot.visibility = GONE
            shimmerRecyclerViewLayout.visibility = VISIBLE
        }
    }
}
