(ns blitzcrank.endpoints.api
  "Methods to access the raw API"
  (:require [blitzcrank.http :as http]
            [blitzcrank.env :as env]
            [blitzcrank.util :refer [valid-api-key? map-to-query-string]]
            [clojure.core.strint :refer [<<]]
            [cheshire.core :refer :all]))

(defn get-resource
  "Primary method to get a valid URL for API access"
  [resource route & [options]]
  (let [{:keys [api-key region path-params query-map]
         :or   {api-key     (env/get-api-key)
                region      (env/get-region)
                path-params nil
                query-map   nil
                route       nil}} options
        query-params (if query-map (<< "?~{(map-to-query-string query-map)}"))
        url (<< "https://~{region}.api.riotgames.com/lol/~{resource}/v3/~{route}/~{path-params}~{query-params}")]
    (if (true? (valid-api-key? api-key)) (prn "Invalid API key"))
    (http/get-resource url {:headers {:X-Riot-Token api-key}})))

(defn get-resource-body
  "Get a valid URL and return the body"
  [resource route & [options]]
  (parse-string (:body (get-resource resource route options)) true))
