(ns lol-api.api.v3.api
  (:require [lol-api.api.http :as http]
            [lol-api.env :as env]
            [lol-api.util :refer [map-to-query-string]]
            [clojure.core.strint :refer [<<]]
            [cheshire.core :refer :all]))

(defn get-resource
  "Primary method to get a valid URL for API access"
  [resource route & [options]]
  (let [{:keys [api-key region path-params query-map version]
         :or   {api-key     (env/get-api-key)
                region      (env/get-region)
                version     (env/get-version)
                path-params nil
                query-map   nil
                route       nil}} options
        query-params (if query-map (<< "?~{(map-to-query-string query-map)}"))
        url (<< "https://~{region}.api.riotgames.com/lol/~{resource}/~{version}/~{route}/~{path-params}~{query-params}")]
    (http/get-resource url {:headers {:X-Riot-Token api-key}})))

(defn get-resource-body
  "Get a valid URL and return the body"
  [resource route & [options]]
  (parse-string (:body (get-resource resource route options)) true))