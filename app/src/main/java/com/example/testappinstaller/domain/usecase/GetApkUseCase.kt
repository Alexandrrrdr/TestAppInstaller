package com.example.testappinstaller.domain.usecase

import com.example.testappinstaller.domain.repository.Repository
import javax.inject.Inject

class GetApkUseCase @Inject constructor(private val repository: Repository) {
    suspend fun getFile(url: String): Long{
        return repository.getApkFile(url = url)
    }
}