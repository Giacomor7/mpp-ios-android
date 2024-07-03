package com.jetbrains.handson.mpp.mobile

import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.withContext

class StationsHelper(apiKey: String) {
    val stations = arrayOf<Station>(
        Station("London Kings Cross", "KGX"),
        Station("Edinburgh Waverley", "EDB"),
        Station("Newcastle", "NCL"),
        Station("York", "YRK"),
        Station("Leeds", "LDS")
    )

    private val client = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        defaultRequest {
            header(
                "x-api-key",
                apiKey
            )
        }
    }

    suspend fun getLiveInfo(departStation: Station, arrivalStation: Station, currentTime: String) = withContext(AppDispatchersImpl().io) {
        var urlStringBuilder = "https://mobile-api-softwire2.lner.co.uk/v1/fares?originStation="
        urlStringBuilder += departStation.code
        urlStringBuilder += "&destinationStation="
        urlStringBuilder += arrivalStation.code
        urlStringBuilder += "&outboundDateTime="
        urlStringBuilder += currentTime
        urlStringBuilder += "&numberOfChildren=0&numberOfAdults=1"
        println(urlStringBuilder)

        val response: HttpResponse = client.get(urlStringBuilder)
        println(response)


    }

}