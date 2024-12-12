package com.dicoding.yubi_apps.ui.analyze

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import android.Manifest


class AnalyzeFragment : Fragment() {
    private var _binding: FragmentAnalyzeBinding? = null
    private val binding get() = _binding!!
    private var uri: Uri? = null
    private var isAnalyzed = false

    private val STORAGE_PERMISSION_CODE = 101

    private val repository by lazy { UploadRepository(ApiConfig.getApiService()) }

    private fun checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_CODE
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val viewModel: UploadViewModel by lazy {
        val factory = UploadViewModelFactory(repository)
        ViewModelProvider(this, factory)[UploadViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalyzeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkAndRequestPermissions()

        binding.galleryButton.setOnClickListener {
            pickImageFromGallery()
        }

        binding.analyzeButton.setOnClickListener {
            if (isAnalyzed) {
                Toast.makeText(context, "Analysis already completed!", Toast.LENGTH_SHORT).show()
            } else {
                if (uri == null) {
                    Toast.makeText(context, "Please select an image first!", Toast.LENGTH_SHORT).show()
                } else {
                    uploadImageToServer()
                }
            }
        }

        val toolbar = binding.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }


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

    private fun uploadImageToServer() {
        if (uri != null) {
            val inputStream = requireContext().contentResolver.openInputStream(uri!!)
            if (inputStream != null) {
                val tempFile = File(requireContext().cacheDir, "temp_image.jpg")
                tempFile.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }

                val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), tempFile)
                val multipartBody = MultipartBody.Part.createFormData("file", tempFile.name, requestFile)
                viewModel.uploadImage(multipartBody)
                viewModel.uploadResult.observe(viewLifecycleOwner) { result ->
                    if (result != null) {
                        isAnalyzed = true
                        val action = AnalyzeFragmentDirections
                            .actionAnalyzeFragmentToGenerateFragment(result.predictedClass, uri.toString())
                        findNavController().navigate(action)
                    }
                }

                viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
                    if (error != null) {
                        Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
                        Log.e("Upload Error", "Error: $error")
                    }
                }

                viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                }
            } else {
                Toast.makeText(context, "Failed to open file", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "No file selected", Toast.LENGTH_SHORT).show()
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            uri = data?.data
            binding.previewImageView.setImageURI(uri)

            uri?.let {
                requireContext().contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }

            Toast.makeText(context, "Image selected!", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        private const val REQUEST_IMAGE_PICK = 100
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
