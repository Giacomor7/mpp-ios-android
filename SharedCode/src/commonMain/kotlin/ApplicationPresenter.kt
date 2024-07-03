package com.jetbrains.handson.mpp.mobile

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ApplicationPresenter: ApplicationContract.Presenter() {

    private val dispatchers = AppDispatchersImpl()
    private var view: ApplicationContract.View? = null
    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job

    override fun onViewTaken(view: ApplicationContract.View) {
        this.view = view
    }

    fun sendInfoRequest(apiKey: String, departStation: Station, arrivalStation: Station, currentTime: String) {
        launch(coroutineContext) {
            StationsHelper(apiKey).getLiveInfo(departStation, arrivalStation, currentTime)
        }
    }
}
