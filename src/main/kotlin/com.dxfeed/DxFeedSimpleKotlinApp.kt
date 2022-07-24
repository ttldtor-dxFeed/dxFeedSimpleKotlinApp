package com.dxfeed

import com.dxfeed.models.QDService
import com.dxfeed.tools.Speedometer

fun main() {
    System.setProperty("scheme", "com.dxfeed.api.impl.DXFeedScheme")
    val qdService = QDService(Speedometer(5000))

    //qdService.testHistoryTnsSubscription("192.168.0.149:8888", listOf("AAPL", "IBM", "ETH/USD:GDAX"), 0)
    //qdService.testStreamTnsSubscription("192.168.0.149:8888", listOf("AAPL", "IBM", "ETH/USD:GDAX"), 0)
    qdService.testStreamTnsSubscription("demo.dxfeed.com:7300", listOf("AAPL", "IBM", "ETH/USD:GDAX"), 0)
}