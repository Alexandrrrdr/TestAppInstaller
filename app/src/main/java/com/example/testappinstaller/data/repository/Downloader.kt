package com.example.testappinstaller.data.repository

interface Downloader {
    suspend fun downloadFile(url: String)
}