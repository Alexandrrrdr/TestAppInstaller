package com.example.testappinstaller.domain.usecase

import com.example.testappinstaller.domain.repository.GoogleRepository
import java.io.File

class GetFileUseCase(private val repository: GoogleRepository) {
    suspend fun getFile(url: String): File{
        return repository.getApkFile(url = url)
    }
}