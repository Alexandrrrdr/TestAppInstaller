package com.example.testappinstaller.domain.repository

import java.io.File

interface GoogleRepository {
    fun getApkFile(url: String): File
}