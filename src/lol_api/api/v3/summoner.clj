(ns lol-api.api.v3.summoner
  "API methods for [summoner-v3](https://developer.riotgames.com/api-methods/#summoner-v3)"
  (:require [lol-api.models.summoner :as model]
            [lol-api.api.v3.api :as api]
            [lol-api.env :as env]
            [cheshire.core :refer :all]))

(defn- json-to-summoner
  "Convert a JSON response to a summoner model"
  [json]
  (let [json (parse-string (:body json) true)]
    (model/->Summoner (:profileIconId json) (:name json) (:summonerLevel json) (:revisionDate json) (:id json) (:accountId json))))

(defn get-summoner-by-name
  "Get a summoner by summoner name."
  ([name]
   (get-summoner-by-name name (env/get-region)))
  ([name region]
   (json-to-summoner (api/get-resource-url "summoner" :route "summoners/by-name" :region region :path-params name))))

(defn get-summoner-by-account-id
  "Get a summoner by account ID"
  ([account-id]
   (get-summoner-by-account-id account-id (env/get-region)))
  ([account-id region]
   (json-to-summoner (api/get-resource-url "summoner" :route "summoners/by-account" :region region :path-params account-id))))

(defn get-summoner-by-summoner-id
  "Get a summoner by summoner ID"
  ([id]
   (get-summoner-by-summoner-id id (env/get-region)))
  ([id region]
   (json-to-summoner (api/get-resource-url "summoner" :route "summoners" :region region :path-params id))))

(defn get-account-id-by-name
  "Get the account ID by summoner name"
  ([name]
   (:account-id (get-summoner-by-name name (env/get-region))))
  ([name region]
   (:account-id (get-summoner-by-name name region))))

(defn get-summoner-id-by-name
  "Get the summoner ID by summoner name"
  ([name]
   (:id (get-summoner-by-name name (env/get-region))))
  ([name region]
   (:id (get-summoner-by-name name region))))