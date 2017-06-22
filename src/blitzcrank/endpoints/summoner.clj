(ns blitzcrank.endpoints.summoner
  "API methods for [summoner-v3](https://developer.riotgames.com/endpoints-methods/#summoner-v3)"
  (:require [blitzcrank.endpoints.api :as api]
            [clojure.core.strint :refer [<<]]))

(defn by-name
  "Get a summoner by summoner name."
  [name & [options]]
  (api/resource-body "summoner" (<< "summoners/by-name/~{name}") options))

(defn by-account-id
  "Get a summoner by account ID"
  [account-id & [options]]
  (api/resource-body "summoner" (<< "summoners/by-account/~{account-id}") options))

(defn by-id
  "Get a summoner by summoner ID"
  [id & [options]]
  (api/resource-body "summoner" (<< "summoners/~{id}") options))

(defn account-id-by-name
  "Get the account ID by summoner name"
  [name & [options]]
  (:account-id (by-name name options)))

(defn id-by-name
  "Get the summoner ID by summoner name"
  [name & [options]]
  (:id (by-name name options)))