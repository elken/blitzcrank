(ns lol-api.util
  (:require [lol-api.constants :refer :all]
            [clojure.pprint :as pprint]))

(defn indicies
  "Test a predicate on a collection and return the indexes"
  [pred coll]
  (keep-indexed #(when (pred %2) %1) coll))

(defn get-key-by-value
  "Get a value from a collection by value"
  [v collection]
  ((keyword (str v)) collection))

(defn log-format
  "Pretty print log output"
  [& args]
  (with-out-str
    (apply pprint/pprint args)))

(defn map-to-query-string
  "Convert a hash to a URL query string"
  [map]
  (clojure.string/join "&" (for [[k v] map]
                             (str (name k) "=" v))))

(defn regional-proxy?
  "Check if x is a regional proxy identifier"
  [x]
  (not (nil? (some #{x} (map name (keys region-map))))))

(defn region-codes
  "Get all regions"
  []
  (sort (map name (keys (apply merge (map #(get region-map %1) (keys region-map)))))))

(defn region-code?
  "Check if x is a region code"
  [x]
  (some #(= x %1) (region-codes)))

(defn subregions
  "Get regions from a regional proxy"
  [proxy]
  (get region-map (keyword proxy)))

(defn region-to-proxy
  "Find a regional proxy given a region code"
  [key]
  (if (region-code? key)
    (let [key (keyword key)
          region-keys (keys region-map)]
      (name (nth region-keys (first (indicies (complement nil?) (map #(-> region-map %1 key) region-keys))))))))

