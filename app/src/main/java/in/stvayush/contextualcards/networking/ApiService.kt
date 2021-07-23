package `in`.stvayush.contextualcards.networking

import `in`.stvayush.contextualcards.models.CardGroupObject
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/**
 * A gateway to communication with web, which hits the
 * @param ENDPOINT with specified @param BASE_URL and
 * returns Observable [CardGroupObject]
 */

interface ApiService {

    @GET(ENDPOINT)
    fun fetchCards(): Observable<CardGroupObject>

    companion object {
        const val ENDPOINT = "fefcfbeb-5c12-4722-94ad-b8f92caad1ad"
    }

}
