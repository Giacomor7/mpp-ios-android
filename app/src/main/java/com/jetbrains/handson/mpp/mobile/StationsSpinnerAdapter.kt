package com.jetbrains.handson.mpp.mobile

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class StationsSpinnerAdapter(
    private val context: Context, textViewResourceId: Int,
    private val stations: Array<Station>
) : ArrayAdapter<Station>(context, textViewResourceId, stations) {

    override fun getCount(): Int {
        return stations.size
    }

    override fun getItem(position: Int): Station {
        return stations[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
        // TODO: position can be nullable?
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.text = stations[position].name

        return label
    }

    override fun getDropDownView(
        position: Int, convertView: View?,
        parent: ViewGroup
    ): View {
        val label = super.getDropDownView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.text = stations[position].name

        return label
    }
}