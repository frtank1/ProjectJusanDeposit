package com.example.projectjusandeposit.di

import com.example.projectjusandeposit.ui.BlankViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel{
        BlankViewModel()
    }
}