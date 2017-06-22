(ns blitzcrank.endpoints.api
  "Methods to access the raw API"
  (:require [blitzcrank
             [http :as http]
             [env :as env]
             [util :refer [valid-api-key? map-to-query-string]]]
            [clojure.core.strint :refer [<<]]
            [cheshire.core :refer :all]))

(defn get-resource
  "Primary method to get a valid URL for API access"
  [resource route & [options]]
  (if (and (empty? (:api-key options)) (empty? (env/get-api-key)))
    (println "Missing API key")
    (let [{:keys [api-key region query-map]
           :or   {api-key     (env/get-api-key)
                  region      (env/get-region)
                  query-map   nil
                  route       nil}} options
          query-params (if query-map (<< "?~{(map-to-query-string query-map)}"))
          url (<< "https://~{region}.api.riotgames.com/lol/~{resource}/v3/~{route}~{query-params}")]
      (if (false? (valid-api-key? api-key)) (println "Invalid API key") (http/get-resource url {:headers {:X-Riot-Token api-key}})))))

(defn resource-body
  "Get a valid URL and return the body"
  [resource route & [options]]
  (parse-string (:body (get-resource resource route options)) true))
