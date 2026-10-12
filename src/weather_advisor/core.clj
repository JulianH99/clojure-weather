(ns weather-advisor.core
  (:require [weather-advisor.service :refer [get-coords get-weather]])
  (:gen-class))

(defn process-city [city]
  (let [{lat :lat lon :lon} (get-coords city)]
    (if (and lat lon)
      (let [{main :main description :description temp :temp} (get-weather lat lon)]
        (str "Main weather " main "\n" description "\nTemperature is " temp " C "))
      "Error getting city weather. Check your API key exists and the city name is real")))

(defn -main
  "Gets the weather of your city and displays its main weather and temperature"
  ([] (println "Please provide a city name"))
  ([& [city & _]] (println (str "your city is " city))
                  (println (process-city city))))

