#!/bin/bash
for counter in $(seq 1 1000); do 
	echo "$counter";
	curl --header "Content-Type: application/json" --request POST --data '{"name": "south-africa"}' https://spring-cloud-function.azurewebsites.net/api/covidfunction
 done


