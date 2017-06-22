(ns blitzcrank.endpoints.runes
  "API methods for [runes-v3](https://developer.riotgames.com/api-methods/#runes-v3)"
  (:require [blitzcrank.endpoints
             [api :as api]
             [summoner :as summoner]]))

(defn rune-pages-by-id
  "Get all rune pages for a summoner by summoner ID"
  [summoner-id & [options]]
  (api/get-resource-body "platform" "runes/by-summoner" (merge {:path-params summoner-id} options)))

(defn rune-pages-by-name
  "Get all rune pages for a summoner by summoner name"
  [name & [options]]
  (rune-pages-by-id (summoner/id-by-name name) options))