package com.example.testappinstaller.data.remote

import retrofit2.http.GET
import java.io.File

interface RetrofitService {

    @GET()
    suspend fun getFile(): File
}