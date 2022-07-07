package training.archcomps.bookstore.bookslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_books_list.*
import timber.log.Timber
import training.archcomps.bookstore.Book
import training.archcomps.bookstore.R
import training.archcomps.bookstore.bookdetail.BookDetailActivity

class BooksListActivity : AppCompatActivity(), BooksListAdapter.BooksListAdapterListener {

    private lateinit var viewModel: BooksListViewModel
    private lateinit var booksAdapter: BooksListAdapter
    private lateinit var books: MutableList<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_list)

        books = mutableListOf()
        booksAdapter = BooksListAdapter(books, this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@BooksListActivity)
            adapter = booksAdapter
        }

        swipeRefresh.setOnRefreshListener { viewModel.refreshBooks() }

        viewModel = ViewModelProviders.of(this).get(BooksListViewModel::class.java)
        viewModel.books.observe(this, Observer { newBooks -> updateBooks(newBooks!!) })
    }

    private fun updateBooks(newBooks: List<Book>) {
        Timber.d("List of books $newBooks")
        books.clear()
        books.addAll(newBooks)
        booksAdapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
    }

    override fun onBookSelected(book: Book) {
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra(BookDetailActivity.EXTRA_BOOK_ID, book.id)
        startActivity(intent)
    }
}
