(ns blitzcrank.http
  "HTTP interfacing methods"
  (:require [blitzcrank.util :as util]
            [blitzcrank.constants :refer [error-code-map]]
            [clj-http.client :as http]
            [cheshire.core :as json]
            [clojure.tools.logging :as log]
            [clojure.core.strint :refer [<<]])
  (:use [slingshot.slingshot :only [throw+]]
        [blitzcrank.macros :only [try-all]]))

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
  (try-all
    (req-fn url options)
    (catch-all [:status (keys error-code-map)] {:keys [request-time headers body status]}
               (log/warn (log-exception (util/get-key-by-value status error-code-map) headers body request-time)))
    (catch Object _
      (log/error (:throwable &throw-context) "Unexpected error occurred")
      (throw+))))

(defn get-resource
  "GET from the url with params"
  ([url]
   (get-resource url nil))
  ([url params]
     (println (<< "Attempting to get ~{url}"))
     (handle-response http/get url params)))