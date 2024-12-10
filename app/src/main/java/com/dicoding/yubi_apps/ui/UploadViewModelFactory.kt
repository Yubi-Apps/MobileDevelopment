package com.dicoding.yubi_apps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.yubi_apps.data.repository.UploadRepository

class UploadViewModelFactory(private val repository: UploadRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UploadViewModel::class.java)) {
            return UploadViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}