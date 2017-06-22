(ns blitzcrank.endpoints.status
  "API methods for [status-v3](https://developer.riotgames.com/endpoints-methods/#lol-status-v3)"
  (:require [blitzcrank
             [util :refer [region-codes]]
             [env :as env]]
            [blitzcrank.endpoints
             [api :as api]]
            [clojure.core.strint :refer [<<]]))

(defn translate [shard-data & [locale]]
  (letfn [(choose-translation [translations]
            (let [applicable (filter #(= (:locale %) (or locale (env/get-locale)))
                                     translations)]
              (when (= 1 (count applicable))
                (:content (first applicable)))))
          (translate-update [update]
            (-> update
                (assoc :content (or (choose-translation (:translations update))
                                    (:content update)))
                (dissoc :translations)))]
    (for [user (:services shard-data)]
      (update user :incidents
              (fn [incidents]
                (for [incident incidents]
                  (update incident :updates
                          (fn [updates]
                            (for [update updates]
                              (translate-update update))))))))))

(defn for-region
  "Get API status for all the endpoints in a given region"
  [region & [options]]
  (letfn [(result []
            (api/resource-body "status" "shard-data/~{region}" options))]
    (if (:translate? options)
      (translate (result) (or (:locale options) (env/get-locale))) (result))))

(defn for-all
  "Get API status for all endpoints in all regions"
  []
  (map for-region (remove #(= %1 "na") (region-codes))))