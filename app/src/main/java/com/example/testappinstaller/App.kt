//package com.example.testappinstaller
//
//import android.app.Application
//import com.example.testappinstaller.di.AppComponent
//import com.example.testappinstaller.di.AppModule
//import com.example.testappinstaller.di.DaggerAppComponent
//
//class App: Application() {
//    lateinit var appComponent: AppComponent
//
//    override fun onCreate() {
//        super.onCreate()
//        appComponent = DaggerAppComponent
//            .builder()
//            .appModule(AppModule(context = this))
//            .build()
//    }
//}