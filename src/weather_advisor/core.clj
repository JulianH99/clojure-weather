(ns weather-advisor.core
  (:require [weather-advisor.env :refer [get-env]]
            [weather-advisor.json :refer [call]])

  (:gen-class))

(def env (get-env))
(def api-url "http://api.openweathermap.org/geo/1.0/direct")
(def weather-url "https://api.openweathermap.org/data/2.5/weather")

(defn get-lat-lon [city]
  (let [geocode (call api-url {:q city :appid (:OPEN_WEATHER_API  env) :limit 1})
        {lat :lat lon :lon} (first geocode)]
    {:lat lat :lon lon}))

(defn get-weather [lat lon]
  (let [weather-response (call weather-url {:lat lat :lon lon :appid (:OPEN_WEATHER_API env) :units "metric"})
        {[weather] :weather {:keys [temp]} :main} weather-response]
    (str (:main weather) " - " (:description weather) " with a temperature of " temp " C ")))

(defn process-city [city]
  (let [{lat :lat lon :lon} (get-lat-lon city)]
    (get-weather lat lon)))

(defn -main
  "I don't do a whole lot ... yet."
  ([] (println "Please provide a city name"))
  ([& [city & _]] (println (str "your city is " city))
                  (println (process-city city))))

