package com.dicoding.yubi_apps.ui.history

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
import com.dicoding.yubi_apps.History
import com.dicoding.yubi_apps.HistoryAdapter
import com.dicoding.yubi_apps.R

class HistoryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoryAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvHistory)
        progressBar = view.findViewById(R.id.progressBar)

        // Dummy data untuk testing
        val history = listOf(
            History(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul History",
                imageResId = R.drawable.picture
            ),
            History(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul History",
                imageResId = R.drawable.picture
            ),
            History(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul History",
                imageResId = R.drawable.picture
            ),
            History(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul History",
                imageResId = R.drawable.picture
            ),
            History(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul History",
                imageResId = R.drawable.picture
            ),
            History(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul History",
                imageResId = R.drawable.picture
            ),
            History(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul History",
                imageResId = R.drawable.picture
            ),
            History(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul History",
                imageResId = R.drawable.picture
            ),
            History(
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                judul = "Judul History",
                imageResId = R.drawable.picture
            )
        )
        adapter = HistoryAdapter(history)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}