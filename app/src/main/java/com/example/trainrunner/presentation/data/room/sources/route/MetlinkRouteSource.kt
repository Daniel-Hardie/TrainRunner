package com.example.trainrunner.presentation.data.room.sources.route

import com.example.trainrunner.presentation.data.room.models.MetlinkRoute

object MetlinkRouteSource {
    val routes = listOf(
        MetlinkRoute(metlinkRouteId = "2", metlinkRouteShortName = "KPL", metlinkRouteLongName = "Kapiti Line (Waikanae - Wellington)", metlinkRouteDesc = "Kapiti Line (Wellington - Waikanae)"),
        MetlinkRoute(metlinkRouteId = "3", metlinkRouteShortName = "MEL", metlinkRouteLongName = "Melling Line (Melling - Wellington)", metlinkRouteDesc = "Melling Line (Melling - Wellington)"),
        MetlinkRoute(metlinkRouteId = "4", metlinkRouteShortName = "WRL", metlinkRouteLongName = "Wairarapa Line (Masterton - Wellington)", metlinkRouteDesc = "Wairarapa Line (Wellington - Masterton)"),
        MetlinkRoute(metlinkRouteId = "5", metlinkRouteShortName = "HVL", metlinkRouteLongName = "Hutt Valley Line (Upper Hutt - Wellington)", metlinkRouteDesc = "Hutt Valley Line (Wellington - Upper Hutt)"),
        MetlinkRoute(metlinkRouteId = "6", metlinkRouteShortName = "JVL", metlinkRouteLongName = "Johnsonville Line (Johnsonville - Wellington)", metlinkRouteDesc = "Johnsonville Line (Wellington - Johnsonville)")
    )
}