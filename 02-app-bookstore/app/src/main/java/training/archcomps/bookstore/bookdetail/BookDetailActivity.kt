package training.archcomps.bookstore.bookdetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*
import training.archcomps.bookstore.Book
import training.archcomps.bookstore.R

class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_BOOK_ID = "bookId"
    }

    private lateinit var viewModel: BookDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val bookId = intent.getIntExtra(EXTRA_BOOK_ID, 1)

        val factory = BooksDetailViewModelFactory(bookId)
        viewModel = ViewModelProviders.of(this, factory).get(BookDetailViewModel::class.java)
        viewModel.book.observe(this, Observer { book -> updateBook(book!!) })
    }

    private fun updateBook(book: Book) {
        Picasso.get()
                .load(book.pictureUrl)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(bookCover)

        bookTitle.text = book.title
        bookAuthor.text = book.author
        bookSummary.text = book.summary
    }

}
