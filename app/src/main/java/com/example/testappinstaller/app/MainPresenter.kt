package com.example.testappinstaller.app

import android.app.Application
import com.example.testappinstaller.data.constants.Constants.MAIN_URL
import com.example.testappinstaller.data.repository.IntentInstallerVersion
import com.example.testappinstaller.data.repository.PackageInstallerVersion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(
    private val intentInstallerVersion: IntentInstallerVersion,
    private val packageInstallerVersion: PackageInstallerVersion,
    private val app: Application
): MvpPresenter<MainView>(){

    private val installer = app.packageManager.packageInstaller
    private val resolver = app.contentResolver

    fun getAppAndInstallViaIntent() {
        CoroutineScope(Dispatchers.IO).launch {
            intentInstallerVersion.downloadFile(MAIN_URL)

        }
    }

    fun getAppAndInstallViaPackInstaller() {
        CoroutineScope(Dispatchers.IO).launch {
            packageInstallerVersion.packageInstallerDownloader(url = MAIN_URL, installer = installer, resolver = resolver)
        }
    }
}