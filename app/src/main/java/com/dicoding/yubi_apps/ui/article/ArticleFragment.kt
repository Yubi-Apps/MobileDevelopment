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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil referensi ke MaterialToolbar
        val toolbar: com.google.android.material.appbar.MaterialToolbar = view.findViewById(R.id.toolbar)

        // Atur navigasi kembali (jika diperlukan)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back) // Tambahkan ikon back
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed() // Kembali ke fragment sebelumnya
        }


        recyclerView = view.findViewById(R.id.rvArticle)
        progressBar = view.findViewById(R.id.progressBar)

        // Dummy data untuk testing
        val articles = listOf(
            Article("Lorem Ipsum is simply dummy text\nof the printing and typesetting industry.", R.drawable.article),
            Article("Lorem Ipsum is simply dummy text\nof the printing and typesetting industry.", R.drawable.article),
            Article("Lorem Ipsum is simply dummy text\nof the printing and typesetting industry.", R.drawable.article)
        )

        adapter = ArticleAdapter(articles)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}