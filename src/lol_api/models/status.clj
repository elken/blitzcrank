(ns lol-api.models.shard-status)

(defrecord ShardStatus
  [name region-tag hostname services slug locales])