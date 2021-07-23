package `in`.stvayush.contextualcards

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

/**
 * Authored by Ayush Shrivastava on 22/7/21
 */

/***
 * Base Application class that extends the [Application] and is capable of providing [Context]
 * & Application Instances all over the application codebase
 */

class ContextualCardsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        lateinit var instance: ContextualCardsApplication
        fun getContext(): Context = instance.applicationContext
    }
}
