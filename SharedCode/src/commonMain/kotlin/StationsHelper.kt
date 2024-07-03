package com.jetbrains.handson.mpp.mobile

import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.withContext
import io.ktor.client.request.*
import io.ktor.client.statement.*

class StationsHelper {
    public val stations = arrayOf<Station>(
        Station("London Kings Cross", "KGX"),
        Station("Edinburgh Waverley", "EDB"),
        Station("Newcastle", "NCL"),
        Station("York", "YRK"),
        Station("Leeds", "LDS")
    )

    // TODO api key :)
    private val API_KEY = "alskdjfal"

    private val client = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        defaultRequest {
            header(
                "x-api-key",
                API_KEY
            )
        }
    }

    fun getUrl(departStation: Station, arrivalStation: Station): String {
        return "https://www.lner.co.uk/travel-information/travelling-now/live-train-times/depart/${departStation.code}/${arrivalStation.code}/#LiveDepResults"
    }

    suspend fun getLiveInfo() = withContext(AppDispatchersImpl().io) {
        val response: HttpResponse = client.get("https://mobile-api-softwire2.lner.co.uk/v1/fares?originStation=KGX&destinationStation=EDB&outboundDateTime=2024-07-09T12:16:27.371Z&numberOfChildren=0&numberOfAdults=1")
        println(response)
    }

}