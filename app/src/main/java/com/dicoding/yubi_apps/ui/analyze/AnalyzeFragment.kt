package com.dicoding.yubi_apps.ui.analyze

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.yubi_apps.R
import com.dicoding.yubi_apps.data.api.retrofit.ApiConfig
import com.dicoding.yubi_apps.data.repository.UploadRepository
import com.dicoding.yubi_apps.databinding.FragmentAnalyzeBinding
import com.dicoding.yubi_apps.ui.UploadViewModel
import com.dicoding.yubi_apps.ui.UploadViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class AnalyzeFragment : Fragment() {
    private var _binding: FragmentAnalyzeBinding? = null
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
        // Inflate layout menggunakan View Binding
        _binding = FragmentAnalyzeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tombol untuk memilih dan mengunggah gambar
        binding.galleryButton.setOnClickListener {
            pickImageFromGallery()
        }
////        // Tambahkan aksi untuk tombol Generate
//        binding.analyzeButton.setOnClickListener {
//            generateResult()
//        }
        // Tambahkan aksi untuk tombol Generate
        binding.analyzeButton.setOnClickListener {
            if (uri == null) {
                Toast.makeText(context, "Please select an image first!", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_analyzeFragment_to_generateFragment,)
                generateResult()
            }
//            Toast.makeText(context, "Analyze button clicked!", Toast.LENGTH_SHORT).show()
//            generateResult()
        }

        // Atur toolbar menggunakan binding
        val toolbar = binding.toolbar

        // Atur navigasi kembali
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back) // Tambahkan ikon back
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed() // Kembali ke fragment sebelumnya
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

//    private fun getRealPathFromURI(uri: Uri): String? {
//        val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
//        return cursor?.use {
//            it.moveToFirst()
//            val idx = it.getColumnIndex("_data")
//            it.getString(idx)
//        } ?: uri.path
//    }
private fun getRealPathFromURI(uri: Uri): String? {
    val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
    return cursor?.use {
        if (it.moveToFirst()) {
            val idx = it.getColumnIndex("_data")
            if (idx != -1) it.getString(idx) else null
        } else {
            null
        }
    } ?: uri.path
}

//    private fun generateResult() {
//        // Pantau perubahan data pada LiveData uploadResult
//        viewModel.uploadResult.observe(viewLifecycleOwner) { result ->
//            if (!result.isNullOrEmpty()) {
//                // Navigasi ke GenerateFragment menggunakan Navigation
//                val bundle = Bundle().apply {
//                    putString("RESULT_KEY", "Generated Result: $result")
//                }
//                findNavController().navigate(R.id.action_analyzeFragment_to_generateFragment, bundle)
//            } else {
//                // Jika tidak ada hasil, tampilkan pesan kesalahan
//                Toast.makeText(requireContext(), "No result available", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
private fun generateResult() {
    viewModel.uploadResult.observe(viewLifecycleOwner) { result ->
        Log.d("AnalyzeFragment", "Raw Result: $result")

        if (result != null) {
            val imageUriString = uri?.toString()
            Log.d("AnalyzeFragment", "Image URI: $imageUriString")

            if (imageUriString != null) {
                val bundle = Bundle().apply {
                    putString("RESULT_KEY", result.toString()) // Pastikan konversi ke string
                    putString("IMAGE_URI_KEY", imageUriString)
                }
                findNavController().navigate(R.id.action_analyzeFragment_to_generateFragment, bundle)
            } else {
                Toast.makeText(requireContext(), "No image selected", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Failed to process image", Toast.LENGTH_SHORT).show()
        }
    }
}

//    @Deprecated("Deprecated in Java")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
//            val uri = data?.data ?: return
//
//            // Tampilkan gambar di ImageView
//            binding.previewImageView.setImageURI(uri)
//
//            // Proses file untuk diunggah
//            val realPath = getRealPathFromURI(uri)
//            if (realPath != null) {
//                val file = File(realPath)
//                val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
//                val multipartBody = MultipartBody.Part.createFormData("imageFile", file.name, requestFile)
//
//                // Upload gambar
//                viewModel.uploadImage(multipartBody)
//                Toast.makeText(context, "Image uploaded successfully!", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(context, "Failed to get file path", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
@Deprecated("Deprecated in Java")
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
        uri = data?.data // Simpan URI gambar di variabel `uri`

        // Tampilkan gambar di ImageView
        binding.previewImageView.setImageURI(uri)

        // Proses file untuk diunggah
        val realPath = getRealPathFromURI(uri!!)
        if (realPath != null) {
            val file = File(realPath)
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val multipartBody = MultipartBody.Part.createFormData("imageFile", file.name, requestFile)

            // Upload gambar ke server
            viewModel.uploadImage(multipartBody)

            Toast.makeText(context, "Image uploaded successfully!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Failed to get file path", Toast.LENGTH_SHORT).show()
        }
    }
}

    companion object {
        private const val REQUEST_IMAGE_PICK = 100
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Hindari memory leak
    }
}