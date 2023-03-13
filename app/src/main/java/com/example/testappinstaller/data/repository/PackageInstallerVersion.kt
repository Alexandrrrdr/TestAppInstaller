package com.example.testappinstaller.data.repository

import android.content.ContentResolver
import android.content.pm.PackageInstaller
import android.content.pm.PackageInstaller.SessionParams

class PackageInstallerVersion() {

    fun packageInstallerDownloader(url: String, installer: PackageInstaller, resolver: ContentResolver) {
        val packageInstaller: Unit = context.PackageManager.PackageInstaller
        val sessionParams = SessionParams(PackageInstallMode.FullInstall)
        val sessionId: Int = packageInstaller.CreateSession(sessionParams)
        val session: Unit = packageInstaller.OpenSession(sessionId)
    }
}