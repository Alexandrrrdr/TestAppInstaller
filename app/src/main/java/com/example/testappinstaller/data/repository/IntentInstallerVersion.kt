package com.example.testappinstaller.data.repository

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.example.testappinstaller.BuildConfig
import java.io.File


class IntentInstallerVersion(private val context: Context) {

    companion object {
        private const val FILE_NAME = "app-release.apk"
        private const val FILE_BASE_PATH = "file://"
        private const val MIME_TYPE = "application/vnd.android.package-archive"
        private const val PROVIDER_PATH = ".provider"
        private const val APP_INSTALL_PATH = "application/vnd.android.package-archive"
    }

    suspend fun downloadFile(url: String) {

        //check if file already exists we delete it.
        var destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .toString() + "/"
        destination += FILE_NAME
        val uri = Uri.parse("$FILE_BASE_PATH + $destination")
        val file = File(destination)
        if (file.exists()) file.delete()

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(url.toUri())
            .setMimeType(MIME_TYPE)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("Download $FILE_NAME")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, FILE_NAME)

        showInstallOption(destination = destination, uri = uri)
        downloadManager.enqueue(request)
    }

    private fun showInstallOption(destination: String, uri: Uri) {
        val onComplete = object : BroadcastReceiver(){
            override fun onReceive(context: Context, intent: Intent) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    val contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + PROVIDER_PATH, File(destination))
                    val installFile = Intent(Intent.ACTION_VIEW)
                    installFile.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    installFile.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    installFile.setDataAndType(contentUri, APP_INSTALL_PATH)
                    installFile.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true)
                    installFile.data = contentUri
                    context.startActivity(installFile)
                    context.unregisterReceiver(this)

                } else {
                    val install = Intent(Intent.ACTION_VIEW)
                    install.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    install.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    install.setDataAndType(uri, APP_INSTALL_PATH)
                    context.startActivity(install)
                    context.unregisterReceiver(this)
                }
            }
        }
        context.registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }


}