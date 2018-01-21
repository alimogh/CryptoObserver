package org.room76.cryptoobserver

import java.sql.Date
import java.sql.Timestamp
import java.util.*


object Utils {

    interface Action<in T> {
        fun action(data:T)
    }

    fun ohlcKrakenToChart(values: Model.OhlcKraken): Model.Chart {
        val chartData = ArrayList<Model.ChartData>()
        for (p in values.body.pair) {
            val average = (p[2].toString().toFloat()+ p[3].toString().toFloat()) / 2
            val date = Calendar.getInstance()
            date.time = Date(Timestamp((p[0] as Double).toLong()).time)
            chartData.add(Model.ChartData(date, average))
        }
        return Model.Chart("Kraken", "ETH/USD", chartData)
    }
}