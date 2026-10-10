(ns weather-advisor.core
  (:require [weather-advisor.service :refer [get-coords get-weather]])
  (:gen-class))

(defn process-city [city]
  (let [{lat :lat lon :lon} (get-coords city)]
    (if (not (or (nil? lat) (nil? lon)))
      (let [{main :main description :description temp :temp} (get-weather lat lon)]
        (str "Main weather " main "\n" description "\nTemperature is " temp " C "))
      "No city found with that name")))

(defn -main
  "I don't do a whole lot ... yet."
  ([] (println "Please provide a city name"))
  ([& [city & _]] (println (str "your city is " city))
                  (println (process-city city))))

