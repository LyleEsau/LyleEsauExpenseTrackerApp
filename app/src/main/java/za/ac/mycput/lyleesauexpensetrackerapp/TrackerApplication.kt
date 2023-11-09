package za.ac.mycput.lyleesauexpensetrackerapp

import android.app.Application
import za.ac.mycput.lyleesauexpensetrackerapp.data.app_container.AppContainer
import za.ac.mycput.lyleesauexpensetrackerapp.data.app_container.AppDataContainer

class TrackerApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}