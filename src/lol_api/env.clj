(ns lol-api.env
  "Methods to deal with environment settings."
  (:require [config.core :refer [env]]))

(defn get-api-key
  "Get the API key from the environment." []
  (:api-key env))

(defn get-region
  "Get the default region from the environment." []
  (:region env))
