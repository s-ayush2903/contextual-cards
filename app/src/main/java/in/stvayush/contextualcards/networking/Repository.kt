package `in`.stvayush.contextualcards.networking

import `in`.stvayush.contextualcards.models.CardGroupObject
import io.reactivex.rxjava3.core.Observable

/**
 * Layer between android-specific components of codebase, i.e., ViewModels and the gateway to the
 * communication with external web, Via this it is made sure all the interactions happen through
 * this [Repository]
 */
class Repository {
  private val apiDelegate = ApiDelegate.instance

  /**
   * Calls the fetchCards() function defined in [ApiService]
   * @returns the same DataType as described there, i.e., Observable [CardGroupObject]
   */
  fun fetchCardsData(): Observable<CardGroupObject> {
    return apiDelegate.apiService.fetchCards()
  }
}
