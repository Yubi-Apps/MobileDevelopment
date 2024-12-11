package com.dicoding.yubi_apps.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.yubi_apps.Response
import com.dicoding.yubi_apps.data.repository.UploadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class UploadViewModel(private val repository: UploadRepository) : ViewModel() {

    val uploadResult = MutableLiveData<Response>()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun uploadImage(file: MultipartBody.Part) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.uploadImage(file)

                if (response.predictedClass.isNotEmpty()) {
                    // Pastikan respon tidak kosong
                    uploadResult.postValue(response)
                    Log.d("UploadViewModel", "Upload Success: ${response.predictedClass}")
                } else {
                    _errorMessage.postValue("Result is empty")
                    Log.e("UploadViewModel", "Upload Result is empty")
                }
            } catch (e: Exception) {
                // Tangani error jaringan atau parsing
                _errorMessage.postValue(e.message ?: "Unknown error occurred")
                Log.e("UploadViewModel", "Upload Error: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }


}
