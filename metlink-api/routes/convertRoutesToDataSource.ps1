function ConvertJsonToStationDataSource {
    param(
        [string]$inputFilepath,
        [string]$outputFilePath
    )
    if (-not (Test-Path $inputFilepath)){
        Write-Host "JSON file not found with filepath '$($inputFilepath)'"
        Exit 1
    }
    
    $routeArray = Get-Content $inputFilepath | ConvertFrom-Json
    $outputArray = New-Object System.Collections.ArrayList

    foreach ($route in $routeArray){
        # Type of 2 indicates train
        if ($route.route_type -eq 2){
            $routeId = $route.route_id
            $shortName = $route.route_short_name
            $longName = $route.route_long_name
            $desc = $route.route_desc
    
            #MetlinkRoute(metlinkRouteId = "2", metlinkRouteShortName = "s", metlinkRouteLongName = "s", metlinkRouteDesc = "s"),
            $outputString = "MetlinkRoute(metlinkRouteId = ""$($routeId)"", metlinkRouteShortName = ""$($shortName)"", metlinkRouteLongName = ""$($longName)"", metlinkRouteDesc = ""$($desc)""),"
            $outputArray.Add($outputString)
        }
    }


    $outputArray | Out-File -FilePath $outputFilePath -Force
}


$inputFilePath = ".\routes\input-routes.json"
$outputFilePath = ".\routes\data-source-routes.txt"
ConvertJsonToStationDataSource -inputFilepath $inputFilePath -outputFilePath $outputFilePath