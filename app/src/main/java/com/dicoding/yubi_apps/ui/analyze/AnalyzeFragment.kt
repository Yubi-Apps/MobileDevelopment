package com.dicoding.yubi_apps.ui.analyze

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.yubi_apps.R


class AnalyzeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk fragment
        return inflater.inflate(R.layout.fragment_analyze, container, false)
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
    }
}