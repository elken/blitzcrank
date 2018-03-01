(ns blitzcrank.util
  (:require [blitzcrank.constants :refer :all]
            [clojure.pprint :as pprint]))

(defn valid-api-key?
  "Simple regex to verify if a key should be valid"
  [api-key]
  (not= nil (re-matches #"(?i)(?:RGAPI-)?[0-9A-F]{8}-[0-9A-F]{4}-[1-5][0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}" api-key)))

(defn indicies
  "Test a predicate on a collection and return the indexes"
  [pred coll]
  (keep-indexed #(when (pred %2) %1) coll))

(defn first-as-name
  "Return the first argument of a sequence as named"
  [seq]
  (name (first seq)))

(defn not-nil?
  "Checks if a value is not nil"
  [x]
  (not= nil x))

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
  [m]
  (->> m
       (reduce-kv (fn [a k v]
                    (if (sequential? v)
                      (reduce #(conj %1 [k %2]) a v)
                      (conj a [k v]))) '())
       (map (fn [[k v]]
              (str (name k) "=" v)))
       (reverse)
       (clojure.string/join "&")))

(defn regional-proxy?
  "Check if x is a regional proxy identifier"
  [x]
  (not-nil? (some #{x} (map name (keys region-map)))))

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

(defn code-in-region?
  "Check if a code exists in the region"
  [code region]
  (let [region (keyword region)
        code (keyword code)]
    (not-nil? (-> region-map region code))))

(defn region-to-proxy
  "Find a regional proxy given a region code"
  [code]
  (if (region-code? code)
    (let [code (keyword code)
          region-keys (keys region-map)]
      (first-as-name (filter #(code-in-region? code %1) region-keys)))))
