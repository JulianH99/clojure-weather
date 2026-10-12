(ns weather-advisor.service
  (:require [weather-advisor.client :refer [call]]))

(defn get-coords [city]
  (let [response (call "geo/1.0/direct" {:q city  :limit 1})]
    (if-let [body (first (:body response))]
      {:lat (:lat body) :lon (:lon body)}
      {})))

(defn get-weather [lat lon]
  (let [weather-response (call "data/2.5/weather" {:lat lat :lon lon  :units "metric"})
        {:keys [body]} weather-response
        {[{main :main desc :description}] :weather {:keys [temp]} :main} body]
    {:main main :description desc :temp temp}))

