(ns blitzcrank.endpoints.masteries
  "API methods for [masteries-v3](https://developer.riotgames.com/endpoints-methods/#masteries-v3)"
  (:require [blitzcrank.endpoints
             [api :as api]
             [summoner :as summoner]]))

(defn mastery-pages-by-id
  "Get all mastery pages for a summoner by summoner ID"
  [summoner-id & [options]]
  (api/get-resource-body "platform" "masteries/by-summoner" (merge {:path-params summoner-id} options)))

(defn mastery-pages-by-name
  "Get all mastery pages for a summoner by summoner name"
  [summoner-name & [options]]
  (mastery-pages-by-id (summoner/id-by-name summoner-name) options))