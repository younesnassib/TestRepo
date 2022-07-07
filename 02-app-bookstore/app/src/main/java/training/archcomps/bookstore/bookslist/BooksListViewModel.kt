package training.archcomps.bookstore.bookslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import training.archcomps.bookstore.App
import training.archcomps.bookstore.Book

class BooksListViewModel : ViewModel() {

    val books : LiveData<List<Book>> = App.db.bookDao().getAllBooks()

    fun refreshBooks() {
        App.repository.syncBooksNow()
    }
}