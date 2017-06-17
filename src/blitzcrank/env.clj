(ns blitzcrank.env
  "Methods to deal with environment settings."
  (:require [config.core :refer [env]]
            [blitzcrank.util :as util]))

(defn- get-or-default
  "Get a value from the environment, or use a default"
  [key default]
  (let [k ((keyword key) env)]
    (if (or (empty? k) (nil? k)) default k)))

(defn get-api-key
  "Get the API key from the environment."
  []
  (get-or-default "endpoints-key" nil))

(defn get-region
  "Get the default region from the environment."
  []
  (get-or-default "region" "euw1"))

(defn get-locale
  "Get the locale from the environment."
  []
  (get-or-default "locale" (str (java.util.Locale/getDefault))))

(defn get-region-as-proxy
  "Get the default region from the environment as a regional proxy."
  []
  (let [region (get-region)]
    (if (util/regional-proxy? region)
      region
      (if (util/region-code? region)
        (util/region-to-proxy region)))))
