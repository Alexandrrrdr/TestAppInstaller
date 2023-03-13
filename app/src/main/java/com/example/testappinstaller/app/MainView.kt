package com.example.testappinstaller.app

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = SingleStateStrategy::class)
interface MainView: MvpView {
    fun showSuccess()
    fun showError()
}