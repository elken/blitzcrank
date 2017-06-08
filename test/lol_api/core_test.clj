(ns lol-api.core-test
  (:require [clojure.test :refer :all]
            [lol-api.core :refer :all]))

(deftest user-has-changed-key-test
  (testing "User has changed key"
    (is (not= (get-api-key) "TYPE YOUR KEY HERE"))))