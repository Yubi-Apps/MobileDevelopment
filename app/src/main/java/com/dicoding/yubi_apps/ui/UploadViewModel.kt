package com.dicoding.yubi_apps.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.yubi_apps.data.repository.UploadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class UploadViewModel(private val repository: UploadRepository) : ViewModel() {

    private val _uploadResult = MutableLiveData<String?>()
    val uploadResult: LiveData<String?> get() = _uploadResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun uploadImage(imageFile: MultipartBody.Part) {
        _isLoading.value = true
        _errorMessage.value = null

//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = repository.uploadImage(imageFile)
//                _uploadResult.postValue(response.predictedClass)
//            } catch (e: Exception) {
//                _errorMessage.postValue(e.message)
//            } finally {
//                _isLoading.postValue(false)
//            }
//        }
        viewModelScope.launch {
            try {
                val response = repository.uploadImage(imageFile)
                _uploadResult.value = response.predictedClass
            } catch (e: Exception) {
                _uploadResult.value = null
            }
        }
    }
}