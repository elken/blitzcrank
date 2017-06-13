(ns blitzcrank.endpoints.summoner
  "API methods for [summoner-v3](https://developer.riotgames.com/endpoints-methods/#summoner-v3)"
  (:require [blitzcrank.endpoints.api :as api]))

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