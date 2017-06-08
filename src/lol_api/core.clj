(ns lol-api.core
  (:require [environ.core :refer [env]]))

(defn get-api-key []
  (env :api-key))