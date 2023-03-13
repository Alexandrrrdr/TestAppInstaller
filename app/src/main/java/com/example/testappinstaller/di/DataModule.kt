//package com.example.testappinstaller.di
//
//import android.content.Context
//import com.example.testappinstaller.data.repository.RepositoryImpl
//import com.example.testappinstaller.domain.repository.Repository
//import dagger.Module
//import dagger.Provides
//
//@Module
//class DataModule {
//
//    @Provides
//    fun provideRepository(context: Context): Repository{
//        return RepositoryImpl(context = context)
//    }
//}