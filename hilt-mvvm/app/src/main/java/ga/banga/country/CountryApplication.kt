package ga.banga.country

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Romaric BANGA on 8/21/21 at 7:44 AM
 */
@HiltAndroidApp
class CountryApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
  }
}