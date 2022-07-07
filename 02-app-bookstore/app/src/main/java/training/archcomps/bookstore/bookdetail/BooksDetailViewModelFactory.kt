package training.archcomps.bookstore.bookdetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class BooksDetailViewModelFactory(private val bookId: Int)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookDetailViewModel(bookId) as T
    }
}