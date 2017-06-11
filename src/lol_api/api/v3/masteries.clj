(ns lol-api.api.v3.masteries
  "API methods for [masteries-v3](https://developer.riotgames.com/api-methods/#masteries-v3)"
  (:require [lol-api.api.v3.api :as api]
            [lol-api.api.v3.summoner :as summoner]))

(defn get-mastery-pages-by-summoner-id
  "Get all mastery pages for a summoner by summoner ID"
  [summoner-id & [options]]
  (api/get-resource-body "platform" "masteries/by-summoner" (merge {:path-params summoner-id} options)))

(defn get-mastery-pages-by-summoner-name
  "Get all mastery pages for a summoner by summoner name"
  [summoner-name & [options]]
  (get-mastery-pages-by-summoner-id (summoner/get-summoner-id-by-name summoner-name) options))