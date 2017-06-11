(ns lol-api.api.v3.status
  "API methods for [status-v3](https://developer.riotgames.com/api-methods/#lol-status-v3)"
  (:require [lol-api.api.v3.api :as api]
            [lol-api.util :refer [region-codes]]))

(defn get-all-endpoints-for-region
  "Get API status for all the endpoints in a given region"
  [region & [options]]
  (api/get-resource-body "status" "shard-data" (merge {:region region} options)))

(defn get-all-endpoints
  "Get API status for all endpoints in all regions"
  []
  (map get-all-endpoints-for-region (remove #(= %1 "na") (region-codes))))