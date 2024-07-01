package com.jetbrains.handson.mpp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity(), ApplicationContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = ApplicationPresenter()
        presenter.onViewTaken(this)

        val stationNames = Stations().stations.map { station -> station.name }

        populateSpinners(R.id.departSelector, stationNames)
        populateSpinners(R.id.arrivalSelector, stationNames)


    }

    private fun populateSpinners(id: Int, stations: List<String>) {
        var spinner = findViewById<Spinner>(id)
        var spinnerArrayadapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, stations)
        spinner.adapter = spinnerArrayadapter
    }

    override fun setLabel(text: String) {

    }


}
