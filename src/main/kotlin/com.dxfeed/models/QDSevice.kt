package com.dxfeed.models

import com.devexperts.logging.Logging
import com.dxfeed.api.DXEndpoint
import com.dxfeed.event.market.TimeAndSale
import com.dxfeed.tools.Speedometer

class QDService(private val speedometer: Speedometer) {
    private val logger = Logging.getLogging(QDService::class.java)

    fun testHistoryTnsSubscription(address: String, symbols: List<String>, timeout: Long) {
        val calculatedTimout = if (timeout == 0L) 1000000 else timeout

        logger.info("QDService: HistoryTnsSub: Connecting")
        DXEndpoint.newBuilder()
                .build()
                .connect(address).use { endpoint ->
                    endpoint.feed.createTimeSeriesSubscription(TimeAndSale::class.java).use { sub ->
                        sub.fromTime = 0L
                        sub.addEventListener { items ->
                            speedometer.addEvents(items.size.toLong())
                        }
                        sub.addSymbols(symbols)

                        Thread.sleep(calculatedTimout * 1000)

                        logger.info("QDService: HistoryTnsSub: Disconnecting")
                    }
                }
    }

    fun testStreamTnsSubscription(address: String, symbols: List<String>, timeout: Long) {
        val calculatedTimout = if (timeout == 0L) 1000000 else timeout

        logger.info("QDService: StreamTnsSub: Connecting")
        DXEndpoint.newBuilder()
                .build()
                .connect(address).use { endpoint ->
                    endpoint.feed.createSubscription(TimeAndSale::class.java).use { sub ->
                        sub.addEventListener { items ->
                            speedometer.addEvents(items.size.toLong())
                        }
                        sub.addSymbols(symbols)

                        Thread.sleep(calculatedTimout * 1000)

                        logger.info("QDService: StreamTnsSub: Disconnecting")
                    }
                }
    }
}