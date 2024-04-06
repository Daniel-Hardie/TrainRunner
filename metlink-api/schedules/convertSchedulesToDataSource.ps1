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
    foreach ($outboundSchedule in $outboundScheduleTripsArray){
        $tripId = $outboundSchedule.id
        $toWellington = "false"
        foreach($stop in $outboundSchedule.stops){
            $parentStationCode = $stop.stop
            $departTime = $stop.depart
            $orderId = $stop.sequence
            $outputString = "MetlinkSchedule(parentStationCode = ""$($parentStationCode)"", departTime = ""$($departTime)"", tripId = ""($tripId)"", orderId = $($orderId), lineId = $($routeId), toWellington = $($toWellington)),"
            $outputArray.Add($outputString)
        }
    }
    $outputArray | Out-File -FilePath $outputFilePath -Force
}


$inputFilePath = ".\HVL-Schedule-Modified.json"
$outputFilePath = ".\data-source-schedules-HVL.txt"
$routeId = "5"
ConvertJsonToScheduleDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\JVL-Schedule-Modified.json"
$outputFilePath = ".\data-source-schedules-JVL.txt"
$routeId = "6"
ConvertJsonToScheduleDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\KPL-Schedule-Modified.json"
$outputFilePath = ".\data-source-schedules-KPL.txt"
$routeId = "2"
ConvertJsonToScheduleDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\MEL-Schedule-Modified.json"
$outputFilePath = ".\data-source-schedules-MEL.txt"
$routeId = "3"
ConvertJsonToScheduleDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId

$inputFilePath = ".\WRL-Schedule-Modified.json"
$outputFilePath = ".\data-source-schedules-WRL.txt"
$routeId = "4"
ConvertJsonToScheduleDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath -routeId $routeId