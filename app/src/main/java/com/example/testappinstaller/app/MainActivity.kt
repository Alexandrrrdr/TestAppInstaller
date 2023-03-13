package com.example.testappinstaller.app

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import com.example.testappinstaller.data.constants.Constants
import com.example.testappinstaller.data.repository.IntentInstallerVersion
import com.example.testappinstaller.data.repository.PackageInstallerVersion
import com.example.testappinstaller.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainActivity() : MvpAppCompatActivity(), MainView {

    companion object{
        const val PERMISSION_REQUEST_STORAGE = 0
    }

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter
    private val intentInstallerVersion =  IntentInstallerVersion(context = this)
    private val packageInstallerVersion =  PackageInstallerVersion()

    @ProvidePresenter
    fun provideMainPresenter(): MainPresenter{
        return MainPresenter(
            intentInstallerVersion = intentInstallerVersion,
            packageInstallerVersion = packageInstallerVersion,
            app = this.application
        )
    }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInstallIntent.setOnClickListener {
            checkPermission()
        }

        binding.btnInstallPackInstaller.setOnClickListener {
            mainPresenter.getAppAndInstallViaPackInstaller()
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun checkPermission() {



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    Constants.PERMISSION_REQUEST_CODE
                )
            } else {
                mainPresenter.getAppAndInstallViaIntent()
            }
        }
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == PERMISSION_REQUEST_STORAGE) {
//            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                mainPresenter.getAppAndInstallViaIntent()
//            } else {
//                binding.root.showSnackbar(R.string.storage_permission_denied, Snackbar.LENGTH_SHORT)
//            }
//        }
//    }
//
//    private fun checkStoragePermission() {
//        if (checkSelfPermissionCompat(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
//            PackageManager.PERMISSION_GRANTED
//        ) {
//            mainPresenter.getAppAndInstallViaIntent()
//        } else {
//            requestStoragePermission()
//        }
//    }
//    private fun requestStoragePermission() {
//        if (shouldShowRequestPermissionRationaleCompat(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            binding.root.showSnackbar(
//                R.string.storage_access_required,
//                Snackbar.LENGTH_INDEFINITE, R.string.ok
//            ) {
//                requestPermissionsCompat(
//                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                    PERMISSION_REQUEST_STORAGE
//                )
//            }
//        } else {
//            requestPermissionsCompat(
//                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                PERMISSION_REQUEST_STORAGE
//            )
//        }
//    }

    override fun showSuccess() {
        TODO("Not yet implemented")
    }

    override fun showError() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}