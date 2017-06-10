(ns lol-api.util
  (:require [clojure.pprint :as pprint]))

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
  (clojure.string/join "&"
                       (for [[k v] map]
                         (str (name k) "=" v))))