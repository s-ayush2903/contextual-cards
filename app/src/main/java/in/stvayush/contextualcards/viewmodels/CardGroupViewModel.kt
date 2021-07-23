package `in`.stvayush.contextualcards.viewmodels

import `in`.stvayush.contextualcards.ContextualCardsApplication.Companion.getContext
import `in`.stvayush.contextualcards.R
import `in`.stvayush.contextualcards.models.CardGroup
import `in`.stvayush.contextualcards.models.CardGroupObject
import `in`.stvayush.contextualcards.networking.Repository
import `in`.stvayush.contextualcards.utils.SharedPreferenceUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import okio.IOException
import retrofit2.HttpException
import java.util.concurrent.TimeoutException

/**
 * Authored by Ayush Shrivastava on 21/7/21
 */

/**
 * [CardGroupViewModel] communicates to both, [MainActivity] & the [Repository]
 * A networking call received from [MainActivity] is triggered via instantiating the [Repository]
 * and updating the @param [errorMessage] to reflect the changes accordingly to the User.
 * All this is monitored easily as LiveData is being used here, which changes dynamically
 * based on the response received from the server
 */
class CardGroupViewModel : ViewModel() {

    private val TAG = "CardGroupViewModel"
    private val repository = Repository()

    /** Kind of a fetching `status`, of how the communication with the server is going on
     *  It is set tu true on 200 OK response,
     *  and set false otherwise, also the @param [errorMessage] too gets updated
     * */
    var fetchSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * A message to be displayed to the user as `help`, in cases of
     * mis-happenings or poor interaction(s) with the server
     */
    lateinit var errorMessage: String

    /**
     * A list of [CardGroup] to store and map the data received in the JSON form, from the Web API response
     * This mapping takes place via the data models that we have created for different components being received from backend
     */
    lateinit var cardGroup: List<CardGroup>

    fun fetchCards() {
        repository.fetchCardsData().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                object :
                    DisposableObserver<CardGroupObject>() {
                    override fun onNext(cardGroupObject: CardGroupObject) {
                        /** Extract out the list of [CardGroup] from the [CardGroupObject] in the response,
                         * respecting user's preference(if not a first start)  */
                        cardGroup = filterCardList(cardGroupObject.cardGroup)
                        fetchSuccessful.value = true
                        Log.d(
                            TAG,
                            "onNext: Hit the endpoint! Listing data observed from the server:---\n $cardGroup"
                        )
                    }

                    /** Diagnose the error and update the message to be displayed to user accordingly */
                    override fun onError(throwable: Throwable?) {
                        when (throwable) {
                            is IOException -> {
                                errorMessage = getContext().getString(R.string.check_connection)
                                Log.e(TAG, "onError: IO Exception: ", throwable)
                            }
                            is TimeoutException -> {
                                errorMessage = getContext().getString(R.string.timeout_error)
                                Log.e(TAG, "onError: Timed out: ", throwable)
                            }
                            is HttpException -> {
                                errorMessage = getContext().getString(R.string.http_exception)
                                Log.e(TAG, "onError: Http Exception: ", throwable)
                            }
                            else -> {
                                errorMessage = getContext().getString(R.string.unknown_error)
                                throwable?.let { Log.e(TAG, "onError: Unkonwn error: ", it) }

                            }
                        }
                    fetchSuccessful.value = false
                    }

                    override fun onComplete() {
                        Log.d(TAG, "onComplete: Fetched all data successfully")
                    }

                })
    }

    /**
     * Remove the [CardGroup] which user selected to not display,
     * it works on the basis of Id of the [CardGroup],
     * so if user selects not to display a card with id `Xid` then it'll never be displayed,
     * no matter how many times refresh is triggered, as it would have stored the Id of the [CardGroup]
     * It will be visible only when existing local cache of application is cleared, or practically,
     * when the application is un-installed and then installed
     * */
    private fun filterCardList(cardGroupList: List<CardGroup>): List<CardGroup> {
        val filteredList = ArrayList<CardGroup>()

        for (cardGroup in cardGroupList) {
            if (!SharedPreferenceUtils.excludeCardGroup(cardGroup.id.toString())) {
                filteredList.add(cardGroup)
            }
        }
        return filteredList
    }

}
