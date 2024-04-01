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

        $toWellington = "false"
        $fromWellington = "false"

        if($stopId[-1] -match "1" -and $stopId -notmatch "PLIM"){
            $toWellington = "true"
        }
        elseif($stopId[-1] -match "2" -and $stopId -notmatch "PLIM"){
            $fromWellington = "true"
        }
        elseif($stopId -match "PLIM"){
            if($stopId[-1] -match "1" -or $stopId[-1] -match "2"){
                $toWellington = "true"
            }
           elseif($stopId[-1] -match "3"){
                $fromWellington = "true"
            }
            
        }
        else {
            $toWellington = "true"
            $fromWellington = "true"
        }

        $outputString = "Station(metlinkRouteId = ""$($routeId)"", metlinkStopId = ""$($stopId)"", metlinkStopCode = ""$($stopCode)"", metlinkStopName = ""$($stopName)"", metlinkStopLatitude = $($stopLat), metlinkStopLongitude = $($stopLon), metlinkZoneId = $($zoneId), metlinkParentStation = ""$($parentStation)"", toWellington = $($toWellington), fromWellington = $($fromWellington)),"
        $outputArray.Add($outputString)
    }


    $outputArray | Out-File -FilePath $outputFilePath -Force
}


$inputFilePath = ".\input-stations-kapiti.json"
$outputFilePath = ".\data-source-stations-kapiti.txt"
$routeId = "2"
ConvertJsonToStationDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\input-stations-melling.json"
$outputFilePath = ".\data-source-stations-melling.txt"
$routeId = "3"
ConvertJsonToStationDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\input-stations-wairarapa.json"
$outputFilePath = ".\data-source-stations-wairarapa.txt"
$routeId = "4"
ConvertJsonToStationDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\input-stations-hutt-valley.json"
$outputFilePath = ".\data-source-stations-hutt-valley.txt"
$routeId = "5"
ConvertJsonToStationDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\input-stations-johnsonville.json"
$outputFilePath = ".\data-source-stations-johnsonville.txt"
$routeId = "6"
ConvertJsonToStationDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId