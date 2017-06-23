(ns blitzcrank.endpoints.leagues
  "API methods for [leagues-v3](https://developer.riotgames.com/api-methods/#league-v3)"
  (:require [blitzcrank.endpoints.api :as api]
            [clojure.core.strint :refer [<<]]))

(defn challengers-for-queue
  "Get all the challenger players for queue"
  [queue-id & [options]]
  (api/resource-body "league" (<< "challengerleagues/by-queue/~{queue-id}") options))

(defn leagues-for-summoner
  "Get leagues in all queues for a given summoner ID"
  [summoner-id & [options]]
  (api/resource-body "league" (<< "leagues/by-summoner/~{summoner-id}") options))

(defn masters-for-queue
  "Get all the master players for given queue"
  [queue-id & [options]]
  (api/resource-body "league" (<< "masterleagues/by-queue/~{queue-id}") options))

(defn league-positions-for-summoner
  "Get league positions in all queues for a given summoner ID"
  [summoner-id & [options]]
  (api/resource-body "league" (<< "positions/by-summoner/~{summoner-id}") options))