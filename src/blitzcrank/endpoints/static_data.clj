(ns blitzcrank.endpoints.static-data
  "API methods for [lol-static-data-v3](https://developer.riotgames.com/api-methods/#lol-static-data-v3)"
  (:require [blitzcrank.endpoints.api :as api]
            [clojure.core.strint :refer [<<]]))

(defn champions
  "Get all champions"
  [& [options]]
  (api/resource-body "static-data" "champions" options))

(defn champion
  "Get a champion by champion ID"
  [id & [options]]
  (api/resource-body "static-data" (<< "champions/~{id}") options))

(defn items
  "Get all items"
  [& [options]]
  (api/resource-body "static-data" "items" options))

(defn item
  "Get an item by item ID"
  [id & [options]]
  (api/resource-body "static-data" (<< "items/~{id}") options))

(defn language-strings
  "Get all language strings"
  [& [options]]
  (api/resource-body "static-data" "language-strings" options))

(defn languages
  "Get all languages"
  [& [options]]
  (api/resource-body "static-data" "languages" options))

(defn maps
  "Get all maps"
  [& [options]]
  (api/resource-body "static-data" "maps" options))

(defn masteries
  "Get all masteries"
  [& [options]]
  (api/resource-body "static-data" "masteries" options))

(defn mastery
  "Get a mastery by mastery ID"
  [id & [options]]
  (api/resource-body "static-data" (<< "masteries/~{id}") options))

(defn profile-icons
  "Get all profile icons"
  [& [options]]
  (api/resource-body "static-data" "profile-icons" options))

(defn realms
  "Get all realms"
  [& [options]]
  (api/resource-body "static-data" "realms" options))

(defn reforged-rune-paths
  "Get all reforged rune paths"
  [& [options]]
  (api/resource-body "static-data" "reforged-rune-paths" options))

(defn reforged-rune-path
  "Get a reforged rune path by ID"
  [id & [options]]
  (api/resource-body "static-data" (<< "reforged-rune-paths/~{id}") options))

(defn reforged-runes
  "Get all reforged runes"
  [& [options]]
  (api/resource-body "static-data" "reforged-runes" options))

(defn reforged-rune
  "Get a reforged rune by ID"
  [id & [options]]
  (api/resource-body "static-data" (<< "reforged-runes/~{id}") options))

(defn runes
  "Get all runes"
  [& [options]]
  (api/resource-body "static-data" "runes" options))

(defn rune
  "Get a rune by rune ID"
  [id & [options]]
  (api/resource-body "static-data" (<< "runes/~{id}") options))

(defn summoner-spells
  "Get all summoner spells"
  [& [options]]
  (api/resource-body "static-data" "summoner-spells" options))

(defn summoner-spell
  "Get a summoner spell by ID"
  [id & [options]]
  (api/resource-body "static-data" (<< "summoner-spells/~{id}") options))

(defn tarball-links
  "Get tarball links"
  [& [options]]
  (api/resource-body "static-data" "tarball-links" options))

(defn versions
  "Get versions"
  [& [options]]
  (api/resource-body "static-data" "versions" options))
