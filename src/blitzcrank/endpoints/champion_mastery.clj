(ns blitzcrank.endpoints.champion-mastery
  "API methods for [champion-mastery-v3](https://developer.riotgames.com/api-methods/#champion-mastery-v3/)"
  (:require [blitzcrank.endpoints.api :as api]
            [clojure.core.strint :refer [<<]]))

(defn by-id
  "Get all champion mastery entries for a summoner by summoner ID"
  [id & [options]]
  (api/resource-body "champion-mastery" (<< "champion-masteries/by-summoner/~{id}") options))

(defn by-id-for-champion
  "Get a specific champion mastery entry by summoner ID and champion ID"
  [summoner-id champion-id & [options]]
  (api/resource-body "champion-mastery" (<< "champion-masteries/by-summoner/~{summoner-id}/by-champion/~{champion-id}") options))

(defn score-for-summoner
  "Get the total mastery score for a summoner"
  [id & [options]]
  (api/resource-body "champion-mastery" (<< "scores/by-summoner/~{id}") options))