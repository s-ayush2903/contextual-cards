package `in`.stvayush.contextualcards.viewmodels

import `in`.stvayush.contextualcards.ContextualCardsApplication
import `in`.stvayush.contextualcards.ContextualCardsApplication.Companion.getContext
import `in`.stvayush.contextualcards.R
import `in`.stvayush.contextualcards.models.CardGroup
import `in`.stvayush.contextualcards.models.CardGroupObject
import `in`.stvayush.contextualcards.networking.Repository
import `in`.stvayush.contextualcards.utils.SharedPreferenceUtils
import android.util.Log
import androidx.core.content.ContextCompat
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

class CardGroupViewModel : ViewModel() {

    private val TAG = "CardGroupViewModel"
    private val repository = Repository()
    var fetchSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    lateinit var errorMessage: String
    lateinit var cardGroup: List<CardGroup>

    fun fetchCards() {
        repository.fetchCardsData().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                object :
                    DisposableObserver<CardGroupObject>() {
                    override fun onNext(cardGroupObject: CardGroupObject) {
                        cardGroup = filterCardList(cardGroupObject.cardGroup)
                        fetchSuccessful.value = true
                        Log.d(
                            TAG,
                            "onNext: Hit the endpoint! Listing data observed from the server:---\n $cardGroup"
                        )
                    }

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
