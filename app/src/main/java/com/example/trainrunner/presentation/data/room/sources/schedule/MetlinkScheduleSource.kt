package com.example.trainrunner.presentation.data.room.sources.schedule

import com.example.trainrunner.presentation.data.room.models.MetlinkSchedule

object MetlinkScheduleSource {
    val schedules = listOf(
        MetlinkSchedule(parentStationCode = "UPPE", departTime = "04:30", tripId = "TripId1", orderId = 0, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "WALL", departTime = "04:32", tripId = "TripId1", orderId = 1, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "TREN", departTime = "04:35", tripId = "TripId1", orderId = 2, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "HERE", departTime = "04:37", tripId = "TripId1", orderId = 3, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "SILV", departTime = "04:39", tripId = "TripId1", orderId = 4, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "MANO", departTime = "04:42", tripId = "TripId1", orderId = 5, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "POMA", departTime = "04:44", tripId = "TripId1", orderId = 6, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "TAIT", departTime = "04:46", tripId = "TripId1", orderId = 7, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "WING", departTime = "04:49", tripId = "TripId1", orderId = 8, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "NAEN", departTime = "04:51", tripId = "TripId1", orderId = 9, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "EPUN", departTime = "04:53", tripId = "TripId1", orderId = 10, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "WATE", departTime = "04:55", tripId = "TripId1", orderId = 11, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "WOBU", departTime = "04:58", tripId = "TripId1", orderId = 12, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "AVA", departTime = "05:01", tripId = "TripId1", orderId = 13, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "PETO", departTime = "05:03", tripId = "TripId1", orderId = 14, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "NGAU", departTime = "05:09", tripId = "TripId1", orderId = 15, lineId = 5, toWellington = true),
        MetlinkSchedule(parentStationCode = "WELL", departTime = "", tripId = "TripId1", orderId = 16, lineId = 5, toWellington = true),
    )
}