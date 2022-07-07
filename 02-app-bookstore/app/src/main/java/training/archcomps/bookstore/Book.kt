package training.archcomps.bookstore

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Book(
        @PrimaryKey(autoGenerate = true)
        var id: Int,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "author")
        var author: String,

        @ColumnInfo(name = "summary")
        var summary: String,

        @ColumnInfo(name = "picture_url")
        var pictureUrl: String)
