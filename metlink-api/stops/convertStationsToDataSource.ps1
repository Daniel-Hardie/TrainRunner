function ConvertJsonToStationDataSource {
    param(
        [string]$inputFilepath,
        [string]$outputFilePath,
        [string]$routeId
    )
    if (-not (Test-Path $inputFilepath)){
        Write-Host "JSON file not found with filepath '$($inputFilepath)'"
        Exit 1
    }
    
    $stationArray = Get-Content $inputFilepath | ConvertFrom-Json
    $outputArray = New-Object System.Collections.ArrayList

    foreach ($station in $stationArray){
        $stopId = $station.stop_id
        $stopCode = $station.stop_code
        $stopName = $station.stop_name
        $stopLat = $station.stop_lat
        $stopLon = $station.stop_lon
        $zoneId = $station.zone_id
        $parentStation = $station.parent_station

        #StationObject(metlinkRouteId = "s", metlinkStopId = "s", metlinkStopCode = "s", metlinkStopName = "s", metlinkStopLatitude = 0.00f, metlinkStopLongitude = 0.00f, metlinkZoneId = 0, metlinkParentStation = "S"),
        $outputString = "StationObject(metlinkRouteId = ""$($routeId)"", metlinkStopId = ""$($stopId)"", metlinkStopCode = ""$($stopCode)"", metlinkStopName = ""$($stopName)"", metlinkStopLatitude = $($stopLat)f, metlinkStopLongitude = $($stopLon)f, metlinkZoneId = $($zoneId), metlinkParentStation = ""$($parentStation)""),"
        $outputArray.Add($outputString)
    }


    $outputArray | Out-File -FilePath $outputFilePath -Force
}


$inputFilePath = ".\stops\input-stations-kapiti.json"
$outputFilePath = ".\stops\data-source-stations-kapiti.txt"
$routeId = "2"
ConvertJsonToStationDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\stops\input-stations-melling.json"
$outputFilePath = ".\stops\data-source-stations-melling.txt"
$routeId = "3"
ConvertJsonToStationDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\stops\input-stations-wairarapa.json"
$outputFilePath = ".\stops\data-source-stations-wairarapa.txt"
$routeId = "4"
ConvertJsonToStationDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\stops\input-stations-hutt-valley.json"
$outputFilePath = ".\stops\data-source-stations-hutt-valley.txt"
$routeId = "5"
ConvertJsonToStationDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\stops\input-stations-johnsonville.json"
$outputFilePath = ".\stops\data-source-stations-johnsonville.txt"
$routeId = "6"
ConvertJsonToStationDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId