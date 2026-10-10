(ns weather-advisor.service
  (:require [weather-advisor.client :refer [call]]))

(defn get-coords [city]
  (let [geocode (call "geo/1.0/direct" {:q city  :limit 1})
        {lat :lat lon :lon} (first geocode)]
    {:lat lat :lon lon}))

(defn get-weather [lat lon]
  (let [weather-response (call "data/2.5/weather" {:lat lat :lon lon  :units "metric"})
        {[{main :main desc :description}] :weather {:keys [temp]} :main} weather-response]
    {:main main :description desc :temp temp}))

