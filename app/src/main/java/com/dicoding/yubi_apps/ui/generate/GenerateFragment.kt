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
        // Ambil data yang dikirimkan lewat Bundle
        val result = arguments?.getString("RESULT_KEY")
        val imageUriString = arguments?.getString("IMAGE_URI_KEY")

        // Log data yang diterima
        Log.d("GenerateFragment", "Received result: $result, Image URI: $imageUriString")

        if (result != null) {
            binding.textView.text = result
        } else {
            binding.textView.text = "No Result Found"
        }

        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            binding.previewImageView.setImageURI(imageUri)
        } else {
            Log.d("GenerateFragment", "Image URI is null.")
        }
    }

//        // Jika ingin tetap mengobservasi uploadResult
//        viewModel.uploadResult.observe(viewLifecycleOwner) { uploadResult ->
//            binding.textView.text = "Generated Result: $uploadResult"
//        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Hindari memory leak
    }
}