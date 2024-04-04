function ConvertJsonToScheduleDataSource {
    param(
        [string]$inputFilepath,
        [string]$outputFilePath,
        [string]$routeId
    )
    if (-not (Test-Path $inputFilepath)){
        Write-Host "JSON file not found with filepath '$($inputFilepath)'"
        Exit 1
    }
    
    $scheduleContent = Get-Content $inputFilepath | ConvertFrom-Json
    $inboundScheduleTripsArray = $scheduleContent.inbound.trips
    $outboundScheduleTripsArray = $scheduleContent.outbound.trips

    $outputArray = New-Object System.Collections.ArrayList

    foreach ($inboundSchedule in $inboundScheduleTripsArray){
        $tripId = $inboundSchedule.id
        $toWellington = "true"
        foreach($stop in $inboundSchedule.stops){
            $parentStationCode = $stop.stop
            $departTime = $stop.depart
            $orderId = $stop.sequence
            $outputString = "MetlinkSchedule(parentStationCode = ""$($parentStationCode)"", departTime = ""$($departTime)"", tripId = ""($tripId)"", orderId = $($orderId), lineId = $($routeId), toWellington = $($toWellington)),"
            $outputArray.Add($outputString)
        }
    }

    

    # foreach ($station in $stationArray){
    #     $stopId = $station.stop_id
    #     $stopCode = $station.stop_code
    #     $stopName = $station.stop_name
    #     $stopLat = $station.stop_lat
    #     $stopLon = $station.stop_lon
    #     $zoneId = $station.zone_id
    #     $parentStation = $station.parent_station

    #     #MetlinkSchedule(parentStationCode = "UPPE", departTime = "04:30", tripId = "TripId1", orderId = 0, lineId = 5, toWellington = true),
    
    #     $outputString = "StationObject(metlinkRouteId = ""$($routeId)"", metlinkStopId = ""$($stopId)"", metlinkStopCode = ""$($stopCode)"", metlinkStopName = ""$($stopName)"", metlinkStopLatitude = $($stopLat)f, metlinkStopLongitude = $($stopLon)f, metlinkZoneId = $($zoneId), metlinkParentStation = ""$($parentStation)""),"
    #     $outputArray.Add($outputString)
    # }


    $outputArray | Out-File -FilePath $outputFilePath -Force
}


$inputFilePath = ".\HVL-Schedule-Modified.json"
$outputFilePath = ".\data-source-schedules-HVL.txt"
$routeId = "5"
ConvertJsonToScheduleDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId