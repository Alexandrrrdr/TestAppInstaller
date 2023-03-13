package com.example.testappinstaller.domain.repository

interface Repository {
    suspend fun getApkFile(url: String): Long
}