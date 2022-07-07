package training.archcomps.bookstore.repository

import androidx.work.Worker
import timber.log.Timber
import training.archcomps.bookstore.App

class SyncRepositoryWorker : Worker() {
    override fun doWork(): Result {
        Timber.i("Synchronizing books...")
        val bookApi = FakeBookApi()
        val bookDao = App.db.bookDao()

        bookDao.insertBooks(bookApi.loadBooks())

        return Result.SUCCESS
    }

}