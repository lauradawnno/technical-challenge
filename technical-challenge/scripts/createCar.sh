#!/bin/sh

curl -d '{"make":"Seat","model":"Cordoba", "year":"2003","chassis_id":"12345F","price":200}' -H "Content-Type: application/json" \
-X POST http://localhost:8080/car/
