(ns weather-advisor.client
  (:require [clj-http.client :as client]
            [cheshire.core :refer [decode]]
            [weather-advisor.env :refer [get-env]]))

(def API_URL "https://api.openweathermap.org/")
(def API_KEY (:OPEN_WEATHER_API (get-env)))

(defn call [url params]
  (let [response (client/get (str API_URL url) {:query-params (assoc params :appid API_KEY)})
        json (decode (:body response) true)]
    json))

