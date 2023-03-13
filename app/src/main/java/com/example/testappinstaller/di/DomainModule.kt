//package com.example.testappinstaller.di
//
//import com.example.testappinstaller.domain.repository.Repository
//import com.example.testappinstaller.domain.usecase.GetApkUseCase
//import dagger.Module
//import dagger.Provides
//
//@Module
//class DomainModule {
//
//    @Provides
//    fun provideGetApkUseCase(repository: Repository): GetApkUseCase{
//        return GetApkUseCase(repository = repository)
//    }
//}