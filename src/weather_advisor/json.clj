(ns weather-advisor.json
  (:require [clj-http.client :as client]
            [cheshire.core :refer [decode]]))

(defn call [url params]
  (let [response (client/get url {:query-params params})
        json (decode (:body response) true)]
    json))
