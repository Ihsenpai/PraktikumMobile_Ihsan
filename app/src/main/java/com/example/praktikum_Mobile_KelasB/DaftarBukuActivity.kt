package com.example.praktikum_Mobile_KelasB

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.praktikum_Mobile_KelasB.data.model.BookDoc
import com.example.praktikum_Mobile_KelasB.databinding.ActivityDaftarBukuBinding
import com.example.praktikum_Mobile_KelasB.ui.adapter.BookAdapter
import com.example.praktikum_Mobile_KelasB.ui.fragment.BookDetailFragment
import com.example.praktikum_Mobile_KelasB.viewmodel.MainViewModel

class DaftarBukuActivity : AppCompatActivity(), BookAdapter.OnBookClickListener {
    private lateinit var binding: ActivityDaftarBukuBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = BookAdapter(emptyList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.books.observe(this) { books ->
            adapter.setData(books)
        }

        viewModel.fetchBooks("kotlin")
    }

    override fun onBookClick(book: BookDoc) {
        BookDetailFragment(
            book.title ?: "-",
            book.authorName?.joinToString(", ") ?: "Unknown Author",
            "${book.firstPublishYear}",
            book.coverId ?: 0
        ).show(supportFragmentManager, BookDetailFragment::class.java.simpleName)
    }
}