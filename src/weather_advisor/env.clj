(ns weather-advisor.env
  (:require [clojure.java.io :refer [reader file]]
            [clojure.string :as string]))

(defn- valid-line? [line]
  (let [line (string/trim line)]
    (and
     (not (string/starts-with? line "#"))
     (not (string/blank? line)))))

(defn- parse-line [line]
  (let [line (string/trim (string/replace line #"#.*" ""))
        [k v] (string/split line #"\=" 2)
        k (string/trim k)
        v (string/trim (string/replace v #"\"" ""))]
    [k v]))

(defn- load-env [path]
  (with-open [rdr (reader path)]
    (filter valid-line? (vec (line-seq rdr)))))

(defn- parse-env [lines]
  (map parse-line lines))

(defn- build-map [lines]
  (reduce #(assoc %1 (keyword (first %2)) (second %2)) {} lines))

(defn- read-env
  ([] (read-env ".env"))
  ([path] (if (.exists (file path))
            (build-map (parse-env (load-env path)))
            {})))

(def get-env (memoize read-env))


