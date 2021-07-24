package `in`.stvayush.contextualcards.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * A splash screen that is inspired by the Cold Start Ideology, and displays Application Logo in
 * each start, then gets finishes immediately. We need not inflate any layout in here, in order to
 * avoid the cold start delay
 */
class SplashActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    startActivity(Intent(this, MainActivity::class.java))
    finish()
  }
}
