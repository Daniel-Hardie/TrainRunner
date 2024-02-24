Stop predictions Taita:

https://api.opendata.metlink.org.nz/v1/stop-predictions?stop_id=TAIT

Hypothesis - can work out when the train will next stop at Taita station

Data structure:
{
    "stop_id": "TAIT1",
    "service_id": "HVL",
    "direction": "inbound",
    "operator": "RAIL",
    "origin": {
        "stop_id": "UPPE",
        "name": "UpperHuttStn"
    },
    "destination": {
        "stop_id": "WELL",
        "name": "WELL-All stops"
    },
    "delay": "PT1M41S",
    "vehicle_id": "4279",
    "name": "TaitaStn",
    "arrival": {
        "aimed": "2024-02-21T18:46:00+13:00",
        "expected": "2024-02-21T18:47:41+13:00"
    },
    "departure": {
        "aimed": "2024-02-21T18:46:00+13:00",
        "expected": "2024-02-21T18:49:01+13:00"
    },
    "status": "delayed",
    "monitored": true,
    "wheelchair_accessible": true,
    "trip_id": "HVL__1__3643__RAIL__Rail_MTuWThF-XHol_20240211"
}

Direction:
- inbound = TO Wellington station
- outbound = AWAY Wellington station

