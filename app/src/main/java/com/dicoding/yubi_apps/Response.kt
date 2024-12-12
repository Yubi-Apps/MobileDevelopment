package com.dicoding.yubi_apps

import com.google.gson.annotations.SerializedName

data class Response(
	@field:SerializedName("predicted_class")
	val predictedClass: String
)
