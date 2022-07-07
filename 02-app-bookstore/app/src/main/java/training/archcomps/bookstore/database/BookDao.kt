package training.archcomps.bookstore.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import training.archcomps.bookstore.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAllBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE id = :id")
    fun getBookById(id: Int) : LiveData<Book>

    @Insert
    fun insertBooks(books: List<Book>)
}