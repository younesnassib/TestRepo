package training.archcomps.bookstore.bookslist

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import training.archcomps.bookstore.Book
import training.archcomps.bookstore.R

class BooksListAdapter(private val books: List<Book>,
                       private val listener: BooksListAdapterListener?)
    : RecyclerView.Adapter<BooksListAdapter.ViewHolder>(), View.OnClickListener {

    interface BooksListAdapterListener {
        fun onBookSelected(book: Book)
    }

    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.cardView)!!
        val bookCover = itemView.findViewById<ImageView>(R.id.bookCover)!!
        val bookTitle = itemView.findViewById<TextView>(R.id.bookTitle)!!
        val bookAuthor = itemView.findViewById<TextView>(R.id.bookAuthor)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_book, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        with (holder) {
            cardView.setOnClickListener(this@BooksListAdapter)
            cardView.tag = book
            bookTitle.text = book.title
            bookAuthor.text = book.author

            Picasso.get()
                    .load(book.pictureUrl)
                    .placeholder(R.drawable.ic_placeholder_image)
                    .into(bookCover)
        }

    }

    override fun getItemCount() = books.size

    override fun onClick(view: View) {
        when (view.id) {
            R.id.cardView -> listener?.onBookSelected(view.tag as Book)
        }
    }

}