(ns blitzcrank.endpoints.summoner
  "API methods for [summoner-v3](https://developer.riotgames.com/endpoints-methods/#summoner-v3)"
  (:require [blitzcrank.endpoints.api :as api]))

(defn summoner-by-name
  "Get a summoner by summoner name."
  [name & [options]]
  (api/get-resource-body "summoner" "summoners/by-name" (merge {:path-params name} options)))

(defn summoner-by-account-id
  "Get a summoner by account ID"
  [account-id & [options]]
  (api/get-resource-body "summoner" "summoners/by-account" (merge {:path-params account-id} options)))

(defn summoner-by-id
  "Get a summoner by summoner ID"
  [id & [options]]
  (api/get-resource-body "summoner" "summoners" (merge {:path-params id} options)))

(defn account-id-by-name
  "Get the account ID by summoner name"
  [name & [options]]
  (:account-id (summoner-by-name name options)))

(defn id-by-name
  "Get the summoner ID by summoner name"
  [name & [options]]
  (:id (summoner-by-name name options)))