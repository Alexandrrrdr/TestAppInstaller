package com.example.testappinstaller.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInstaller
import android.content.pm.PackageInstaller.EXTRA_STATUS
import android.util.Log

class DownloadCompletedReceiver() : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        when(val status = intent.getIntExtra(EXTRA_STATUS, -1)){
            PackageInstaller.STATUS_PENDING_USER_ACTION -> {
                val activityIntent = intent.getParcelableExtra<Intent>(Intent.EXTRA_INTENT)
                context.startActivity(activityIntent!!.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }

            PackageInstaller.STATUS_SUCCESS -> {

            }
            else -> {
                val msg = intent.getStringExtra(PackageInstaller.EXTRA_STATUS_MESSAGE)
                Log.e("info", "received $status and $msg")
            }
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            val contentUri = FileProvider.getUriForFile(
//                context,
//                BuildConfig.APPLICATION_ID + FileDownloader.PROVIDER_PATH,
//                File(destination)
//            )
//            val installFile = Intent(Intent.ACTION_VIEW)
//            installFile.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
//            installFile.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//            installFile.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true)
//            installFile.data = contentUri
//            context.startActivity(installFile)
//            context.unregisterReceiver(this)
//
//        } else {
//            val install = Intent(Intent.ACTION_VIEW)
//            install.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//            install.setDataAndType(uri, FileDownloader.APP_INSTALL_PATH)
//            context.startActivity(install)
//            context.unregisterReceiver(this)
//        }
    }
}