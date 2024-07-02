package com.jetbrains.handson.mpp.mobile

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.departSelector


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

        findViewById<Spinner>(R.id.departSelector).setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                departStation = stations[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                departStation = null
            }
        })

        findViewById<Spinner>(R.id.arrivalSelector).setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                arrivalStation = stations[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                arrivalStation = null
            }
        })

        findViewById<Button>(R.id.submitButton)
            .setOnClickListener {
                if(departStation == null || arrivalStation == null) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Error")
                    builder.setMessage("Please select your departure and arrival station.")
                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                        Toast.makeText(applicationContext,
                            android.R.string.yes, Toast.LENGTH_SHORT).show()
                    }

                    builder.show()
                }
                val browserIntent = Intent(Intent.ACTION_VIEW,
                    Uri.parse(stationsHelper.getUrl(departStation!!, arrivalStation!!)))
                startActivity(browserIntent)
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
