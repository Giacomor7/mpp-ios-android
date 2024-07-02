package com.jetbrains.handson.mpp.mobile

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jetbrains.handson.mpp.mobile.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ApplicationContract.View {

    private lateinit var binding: ActivityMainBinding

    private var departStation: Station? = null
    private var arrivalStation: Station? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val presenter = ApplicationPresenter()
        presenter.onViewTaken(this)

        val stationsHelper = StationsHelper()

        val stations = stationsHelper.stations

        populateSpinner(binding.departSelector, stations)
        populateSpinner(binding.arrivalSelector, stations)

        binding.departSelector.onItemSelectedListener = object : OnItemSelectedListener {
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
        }

        binding.arrivalSelector.onItemSelectedListener = object : OnItemSelectedListener {
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
        }

        binding.submitButton.setOnClickListener {
                if(departStation == null || arrivalStation == null) {
                    AlertDialog.Builder(this).apply {
                        setTitle("Error")
                        setMessage("Please select your departure and arrival stations.")
                        setPositiveButton(android.R.string.yes){ dialog, which ->
                            Toast.makeText(applicationContext, android.R.string.yes, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                val browserIntent = Intent(Intent.ACTION_VIEW,
                    Uri.parse(stationsHelper.getUrl(departStation!!, arrivalStation!!)))
                startActivity(browserIntent)
            }
    }

    private fun populateSpinner(spinner: Spinner, stations: Array<Station>) {

        val spinnerArrayAdapter = StationsSpinnerAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, stations)
        spinner.adapter = spinnerArrayAdapter
    }

    override fun setLabel(text: String) {

    }


}
