package com.dicoding.yubi_apps.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.dicoding.yubi_apps.R
import com.dicoding.yubi_apps.ui.analyze.AnalyzeFragment
import com.dicoding.yubi_apps.ui.article.ArticleFragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cari ID CardView di layout fragment_home.xml
        val articleCard: View = view.findViewById(R.id.articleCard)
        val analyzeCard: View = view.findViewById(R.id.analyzeCard)

        // Navigasi menggunakan NavController
        articleCard.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_articleFragment)
        }

        analyzeCard.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_analyzeFragment)
        }
    }
}