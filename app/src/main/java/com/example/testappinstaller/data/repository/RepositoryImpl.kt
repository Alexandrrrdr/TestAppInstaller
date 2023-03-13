//package com.example.testappinstaller.data.repository
//
//import android.content.Context
//import com.example.testappinstaller.domain.repository.Repository
//import javax.inject.Inject
//
//class RepositoryImpl @Inject constructor(context: Context): Repository {
//    private val downloader = FileDownloader(context)
//
//    override suspend fun getApkFile(url: String): Long {
//        return downloader.downloadFile(url = url)
//    }
//}