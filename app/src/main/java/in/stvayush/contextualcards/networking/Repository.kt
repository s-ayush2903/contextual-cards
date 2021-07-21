package `in`.stvayush.contextualcards.networking

import `in`.stvayush.contextualcards.models.CardGroupObject
import io.reactivex.rxjava3.core.Observable

class Repository {
    private val apiDelegate = ApiDelegate.instance

    fun fetchCardsData(): Observable<CardGroupObject> {
        return apiDelegate.apiService.fetchCards()
    }

}
