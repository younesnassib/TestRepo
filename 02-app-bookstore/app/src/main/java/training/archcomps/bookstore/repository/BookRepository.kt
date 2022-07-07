package training.archcomps.bookstore.repository

import androidx.work.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BookRepository {

    private val constraints =  Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
//hola mi hermano
    fun syncBooksNow() {
        Timber.i("Synchronizing books now")
        val work = OneTimeWorkRequestBuilder<SyncRepositoryWorker>()
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance()
                .beginUniqueWork("syncBooksNow", ExistingWorkPolicy.KEEP, work)
                .enqueue()
    }

    fun scheduleBooksSync() {
        Timber.i("Scheduling books sync every 12 hours")
        val work = PeriodicWorkRequestBuilder<SyncRepositoryWorker>(12, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()

       WorkManager.getInstance()
               .enqueueUniquePeriodicWork("syncBooksScheduled", ExistingPeriodicWorkPolicy.KEEP, work)
    }

}