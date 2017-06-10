(ns lol-api.api.v3.api
  (:require [lol-api.api.http :as http]
            [lol-api.env :as env]
            [lol-api.util :as util]
            [clojure.core.strint :refer [<<]]))

(defn get-resource-url
  "Primary method to get a valid URL for API access"
  [resource & {:keys [api-key region path-params query-map route]
               :or   {api-key     (env/get-api-key)
                      region      (env/get-region)
                      path-params nil
                      query-map   nil
                      route       nil}}]
  (let [query-params (if query-map (<< "?~{(util/map-to-query-string query-map)}"))
        route (if route (<< "/~{route}"))
        url (<< "https://~{region}.api.riotgames.com/lol/~{resource}/v3~{route}/~{path-params}~{query-params}")]
    (http/get-resource url {:headers {:X-Riot-Token api-key}})))