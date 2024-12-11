package com.dicoding.yubi_apps.ui.generate

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.yubi_apps.R
import com.dicoding.yubi_apps.data.api.retrofit.ApiConfig
import com.dicoding.yubi_apps.data.repository.UploadRepository
import com.dicoding.yubi_apps.databinding.ActivityMainBinding
import com.dicoding.yubi_apps.databinding.FragmentGenerateBinding
import com.dicoding.yubi_apps.ui.UploadViewModel
import com.dicoding.yubi_apps.ui.UploadViewModelFactory


class GenerateFragment : Fragment() {

    private var _binding: FragmentGenerateBinding? = null
    private val binding get() = _binding!!
    private var uri: Uri? = null
    // Buat instance repository
    private val repository by lazy { UploadRepository(ApiConfig.getApiService()) }

    // Buat instance ViewModel menggunakan factory
    private val viewModel: UploadViewModel by lazy {
        val factory = UploadViewModelFactory(repository)
        ViewModelProvider(this, factory)[UploadViewModel::class.java]
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

        val bottomNavigationView = requireActivity().findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.visibility = View.GONE

        val toolbar: com.google.android.material.appbar.MaterialToolbar = view.findViewById(R.id.toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        val args = GenerateFragmentArgs.fromBundle(requireArguments())
        val predictedClass = args.RESULTKEY
        val imageUri = args.IMAGEURIKEY

        binding.textView.text = "predicted_class = ${predictedClass}"
        binding.previewImageView.setImageURI(Uri.parse(imageUri))

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}