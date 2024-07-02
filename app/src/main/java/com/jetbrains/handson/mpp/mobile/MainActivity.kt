package com.jetbrains.handson.mpp.mobile

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity(), ApplicationContract.View {

    private var departStation: Station? = null
    private var arrivalStation: Station? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = ApplicationPresenter()
        presenter.onViewTaken(this)

        val stationsHelper = StationsHelper()

        val stations = stationsHelper.stations

        populateSpinner(R.id.departSelector, stations)
        populateSpinner(R.id.arrivalSelector, stations)

        findViewById<Button>(R.id.submitButton)
            .setOnClickListener {
//                val browserIntent = Intent(Intent.ACTION_VIEW,
//                    Uri.parse(stationsHelper.getURL()))
                val browserIntent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.google.com"))
                startActivity(browserIntent)
                // TODO: error if either station is null
            }
    }

    private fun populateSpinner(id: Int, stations: Array<Station>) {
        val spinner = findViewById<Spinner>(id)
        val spinnerArrayAdapter = StationsSpinnerAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, stations)
        spinner.adapter = spinnerArrayAdapter
    }

    override fun setLabel(text: String) {

    }


}
