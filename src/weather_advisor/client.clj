(ns weather-advisor.client
  (:require [clj-http.client :as client]
            [cheshire.core :refer [decode]]
            [weather-advisor.env :refer [get-env]]))

(def api-url "https://api.openweathermap.org/")
(defn api-key [] (:OPEN_WEATHER_API (get-env)))

(defn call [url params]
  (let [response (client/get (str api-url url) {:query-params (assoc params :appid (api-key)) :throw-exceptions false})
        status-code (:status response)
        json (decode (:body response) true)]
    (if (= status-code 200)
      {:body json}
      {:error true})))

