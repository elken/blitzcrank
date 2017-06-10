(ns lol-api.api.http
  "HTTP interfacing methods"
  (:require [lol-api.util :as util]
            [lol-api.macros :refer [try-all def-]]
            [clj-http.client :as http]
            [cheshire.core :as json]
            [clojure.tools.logging :as log]
            [clojure.core.strint :refer [<<]])
  (:use [slingshot.slingshot :only [throw+]]))

(defn- print-if-json-body
  "Don't print huge HTML response bodies"
  [headers body]
  (if (= (:content-type headers) "application/json") body))

(defn- log-exception
  "Pretty print exception"
  [status headers body request-time]
  (util/log-format {:status       status
                    :headers      headers
                    :body         (print-if-json-body headers body)
                    :request-time request-time}))

(defn- handle-response
  "Attempt the request and return the response"
  [req-fn url options]
  (let [error-code-map {:400 "Bad request"
                        :401 "Unauthorized"
                        :403 "Forbidden"
                        :404 "Data not found"
                        :405 "Method not allowed"
                        :415 "Unsupported media type"
                        :429 "Rate limit exceeded"
                        :500 "Internal server error"
                        :502 "Bad gateway"
                        :503 "Service unavailable"
                        :504 "Gateway timeout"}]
    (try-all
      (req-fn url options)
      (catch-all [:status (keys error-code-map)] {:keys [request-time headers body status]}
                 (log/warn (log-exception (util/get-key-by-value status error-code-map) headers body request-time)))
      (catch Object _
        (log/error (:throwable &throw-context) "Unexpected error occurred")
        (throw+)))))

(defn get-resource
  "GET from the url with params"
  ([url]
   (get-resource url nil))
  ([url params]
     (println (<< "Attempting to get ~{url} with ~{params}"))
     (handle-response http/get url params)))