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

/**
 * Entry point to the Application, from where majority of the calls are "created"
 */
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val TAG = "MainActivity"

    /** Lazily initialize the [CardGroupViewModel] to avoid delay in Activity creation */
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

    /** Trigger the method in [cardGroupViewModel] and observe the MutableLiveData,
     * [cardGroupViewModel.fetchSuccssful], and handle UI accordingly as per its boolean value */
    private fun fetchData() {
        cardGroupViewModel.fetchSuccessful.observe(this, {
            it?.let {
                with(cardGroupViewModel) {
                    if (it) {
                        renderData(cardGroup)
                    } else {
                        displayErrorScreen(errorMessage)
                    }
                }
            }
        })
    }

    /** Handle shimmer, swipeRefresh state, and visibility of other views,
     * also setCardGroupData for displaying it on screen */
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

    /** Similarly handle views to display the error screen */
    private fun displayErrorScreen(errorMessage: String) {
        with(activityMainBinding) {
            rootSwipeRefreshLayout.isRefreshing = false
            shimmerRecyclerViewLayout.visibility = GONE
            errorLayout.noConnectionLayout.visibility = VISIBLE
            errorLayout.errorMessage.text = errorMessage
        }
    }

    /** Goes the similar way for showing the loading screen */
    private fun displayLoadingScreen() {
        with(activityMainBinding) {
            rootSwipeRefreshLayout.isRefreshing = false
            errorLayout.noConnectionLayout.visibility = GONE
            rvRoot.visibility = GONE
            shimmerRecyclerViewLayout.visibility = VISIBLE
        }
    }
}
