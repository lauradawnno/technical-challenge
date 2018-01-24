#!/bin/sh

curl -d '{"make":"Seat","model":"Cordoba", "year":"2003"}' -H "Content-Type:application/json" \
 -X POST http://localhost:8080/avgprice
