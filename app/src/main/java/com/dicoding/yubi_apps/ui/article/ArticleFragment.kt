package com.dicoding.yubi_apps.ui.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.yubi_apps.Article
import com.dicoding.yubi_apps.ArticleAdapter
import com.dicoding.yubi_apps.R
import com.google.android.material.appbar.MaterialToolbar

class ArticleFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticleAdapter
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: com.google.android.material.appbar.MaterialToolbar = view.findViewById(R.id.toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }


        recyclerView = view.findViewById(R.id.rvArticle)
        progressBar = view.findViewById(R.id.progressBar)

        // Dummy data untuk testing
        val articles = listOf(
            Article(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul Artikel 1",
                imageResId = R.drawable.article
            ),
            Article(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul Artikel 2",
                imageResId = R.drawable.article
            ),
            Article(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul Artikel 3",
                imageResId = R.drawable.article
            ),
            Article(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul Artikel 4",
                imageResId = R.drawable.article
            ),
            Article(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul Artikel 5",
                imageResId = R.drawable.article
            ),
            Article(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul Artikel 6",
                imageResId = R.drawable.article
            ),
            Article(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul Artikel 7",
                imageResId = R.drawable.article
            ),
            Article(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul Artikel 8",
                imageResId = R.drawable.article
            ),
            Article(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul Artikel 9",
                imageResId = R.drawable.article
            )
        )

        adapter = ArticleAdapter(articles)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}