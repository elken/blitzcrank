(ns blitzcrank.endpoints.champion
  "API methods for [champion-v3](https://developer.riotgames.com/api-methods/#champion-v3)"
  (:require [blitzcrank.endpoints.api :as api]))

(defn all
  "Get all champions"
  [& [options]]
  (api/resource-body "platform" "champions" options))

(defn by-id
  "Get a champion by champion ID"
  [id & [options]]
  (api/resource-body "platform" "champions" (merge {:path-params id} options)))

(defn all-ranked
  "Get all champions allowed in ranked"
  [& [options]]
  (filter #(:rankedPlayEnabled %) (:champions (all options))))

(defn all-bots
  "Get all champions allowed as bots in custom games"
  [& [options]]
  (filter #(:botEnabled %) (:champions (all options))))

(defn all-free
  "Get all champions that are currently in the free rotation"
  [& [options]]
  (filter #(:freeToPlay %) (:champions (all options))))

(defn all-custom-bots
  "Get all champions allowed as bots in matchmade games"
  [& [options]]
  (filter #(:botMmEnabled %) (:champions (all options))))

(defn all-active
  "Get all champions that are active"
  [& [options]]
  (filter #(:active %) (:champions (all options))))