(ns lol-api.api.v3.summoner
  "API methods for [summoner-v3](https://developer.riotgames.com/api-methods/#summoner-v3)"
  (:require [lol-api.api.v3.api :as api]
            [clojure.core.strint :refer [<<]]
            [lol-api.env :as env]))

(defn get-summoner-by-name
  "Get a summoner by summoner name."
  [name & [options]]
  (api/get-resource-body "summoner" "summoners/by-name" (merge {:path-params name} options)))

(defn get-summoner-by-account-id
  "Get a summoner by account ID"
  [account-id & [options]]
  (api/get-resource-body "summoner" "summoners/by-account" (merge {:path-params account-id} options)))

(defn get-summoner-by-summoner-id
  "Get a summoner by summoner ID"
  [id & [options]]
  (api/get-resource-body "summoner" "summoners" (merge {:path-params id} options)))

(defn get-account-id-by-name
  "Get the account ID by summoner name"
  [name & [options]]
  (:account-id (get-summoner-by-name name options)))

(defn get-summoner-id-by-name
  "Get the summoner ID by summoner name"
  [name & [options]]
  (:id (get-summoner-by-name name options)))