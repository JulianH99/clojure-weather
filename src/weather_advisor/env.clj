(ns weather-advisor.env
  (:require [clojure.java.io :refer [reader]]
            [clojure.string]))

(defn -load-env []
  (with-open [rdr (reader ".env")]
    (filter #(> (count %) 0) (reduce conj [] (line-seq rdr)))))

(defn -parse-env [lines]
  (map #(clojure.string/split % #"\=") lines))

(defn -build-map [lines]
  (reduce #(assoc %1 (keyword (first %2)) (second %2)) {} lines))

(defn get-env []
  (-build-map (-parse-env (-load-env))))
