## Pre-requisites :
* Java 8+
* Maven
* Any web browser - Chrome, Firefox, IE.

## Description :
This is a demo maven project to compare weather data captured from NDTV website and OpenWeather API.

## Run testcase :
`mvn clean test -Dcity="Bengaluru" -Dtemp_variance="2.0" -Dhumidity_variance="10.0" allure:serve`

Here,
1. *city* : Name of the city whose weather data needs to be tested
1. *temp_variance* : Variance range for temperature comparison
1. *humidity_variance* : Variance range for humidity comparison
1. *allure:serve* : This stage will generate allure html report

## SEQUENCE DIAGRAM :

![Sequence Diagram](seq_diag.png?raw=true "Sequence_Diagram")
