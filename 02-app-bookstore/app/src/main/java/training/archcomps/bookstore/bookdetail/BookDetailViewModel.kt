package training.archcomps.bookstore.bookdetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import training.archcomps.bookstore.App
import training.archcomps.bookstore.Book

class BookDetailViewModel(bookId: Int) : ViewModel() {
    private val bookIdLiveData = MutableLiveData<Int>()

    val book : LiveData<Book> = Transformations.switchMap(bookIdLiveData) { id ->
        App.db.bookDao().getBookById(id)
    }

    init {
        bookIdLiveData.value = bookId
    }
}