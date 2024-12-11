package com.dicoding.yubi_apps.ui.history

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.yubi_apps.Adapter.PredictionHistoryAdapter
import com.dicoding.yubi_apps.R
import com.dicoding.yubi_apps.data.DataRiwayat.AppDatabase
import com.dicoding.yubi_apps.data.DataRiwayat.PredictionHistory
import com.dicoding.yubi_apps.data.repository.HistoryRepository
import com.dicoding.yubi_apps.ui.HistoryViewModel
import com.dicoding.yubi_apps.ui.HistoryViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryFragment : Fragment(), PredictionHistoryAdapter.OnDeleteClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PredictionHistoryAdapter
    private var predictionList: MutableList<PredictionHistory> = mutableListOf()
    private lateinit var tvNotFound: TextView
    private lateinit var progressBar: ProgressBar

    private val historyRepository by lazy {
        HistoryRepository(
            AppDatabase.getDatabase(requireContext()).predictionHistoryDao()
        )
    }

    private val historyViewModel: HistoryViewModel by lazy {
        val historyFactory = HistoryViewModelFactory(historyRepository)
        ViewModelProvider(this, historyFactory)[HistoryViewModel::class.java]
    }

    companion object {
        const val TAG = "history data"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PredictionHistoryAdapter(predictionList)
        adapter.setOnDeleteClickListener(this)
        recyclerView = view.findViewById(R.id.rvHistory)


        historyViewModel.allHistoryData.observe(viewLifecycleOwner) { historyList ->
            if (historyList != null) {
                predictionList.clear()
                predictionList.addAll(historyList)
                adapter.notifyDataSetChanged()
                adapter = PredictionHistoryAdapter(predictionList)
                adapter.setOnDeleteClickListener(this)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = adapter
            }
        }

    }
    override fun onResume() {
        super.onResume()
        historyViewModel.getAllHistory()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDeleteClick(position: Int) {
        val prediction = predictionList[position]
        if (prediction.result.isNotEmpty()) {
            GlobalScope.launch(Dispatchers.IO) {
                AppDatabase.getDatabase(requireContext()).predictionHistoryDao()
                    .deletePrediction(prediction)
            }
            predictionList.removeAt(position)
            requireActivity().runOnUiThread {
                adapter.notifyDataSetChanged()
                if (predictionList.isEmpty()) {
                    recyclerView.visibility = View.GONE
                }
            }
        }
    }
}