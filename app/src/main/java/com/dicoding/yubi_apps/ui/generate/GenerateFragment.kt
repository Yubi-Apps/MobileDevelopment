package com.dicoding.yubi_apps.ui.generate

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.yubi_apps.R
import com.dicoding.yubi_apps.data.DataRiwayat.AppDatabase
import com.dicoding.yubi_apps.data.DataRiwayat.PredictionHistory
import com.dicoding.yubi_apps.data.repository.HistoryRepository
import com.dicoding.yubi_apps.databinding.FragmentGenerateBinding
import com.dicoding.yubi_apps.ui.HistoryViewModel
import com.dicoding.yubi_apps.ui.HistoryViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView


class GenerateFragment : Fragment() {

    private var _binding: FragmentGenerateBinding? = null
    private val binding get() = _binding!!
    private var uri: Uri? = null

    private val historyRepository by lazy {
        HistoryRepository(
            AppDatabase.getDatabase(requireContext()).predictionHistoryDao()
        )
    }

    private val historyViewModel: HistoryViewModel by lazy {
        val historyFactory = HistoryViewModelFactory(historyRepository)
        ViewModelProvider(this, historyFactory)[HistoryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenerateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(
                R.id.nav_view
            )
        bottomNavigationView.visibility = View.GONE

        val toolbar: MaterialToolbar =
            view.findViewById(R.id.toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        val args = GenerateFragmentArgs.fromBundle(requireArguments())
        val predictedClass = args.RESULTKEY
        val imageUri = args.IMAGEURIKEY

        binding.textView.text = "predicted_class = ${predictedClass}"
        binding.previewImageView.setImageURI(Uri.parse(imageUri))

        binding.savebutton.setOnClickListener {
            saveHistory(Uri.parse(imageUri), predictedClass)
            Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.navigation_home)
        }

    }

    private fun saveHistory(imageUri: Uri, result: String) {
        val history = PredictionHistory(
            imagePath = imageUri.toString(), result = result
        )
        historyViewModel.insert(history)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}