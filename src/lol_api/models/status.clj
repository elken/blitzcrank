(ns lol-api.models.status)

(defrecord ShardStatus
  [name region-tag hostname services slug locales])

(defrecord Service
  [status incidents name slug])

(defrecord Incident
  [active created-at id updates])

(defrecord Message
  [severity author created-at translations updated-at content id])

(defrecord Translation
  [locale content updated-at])