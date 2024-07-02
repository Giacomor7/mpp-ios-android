package com.jetbrains.handson.mpp.mobile

class StationsHelper {
    public val stations = arrayOf<Station>(
        Station("London Kings Cross", "KGX"),
        Station("Edinburgh Waverley", "EDB"),
        Station("Newcastle", "NCL"),
        Station("York", "YRK"),
        Station("Leeds", "LDS")
    )

    fun getUrl(departStation: Station, arrivalStation: Station): String {
        return "https://www.lner.co.uk/travel-information/travelling-now/live-train-times/depart/${departStation.code}/${arrivalStation.code}/#LiveDepResults"
    }

}