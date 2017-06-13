(ns blitzcrank.util-test
  (:require [clojure.test :refer :all]
            [blitzcrank.util :refer :all]))

(def ^{:private true} complex-map {:first-parent  {:first-child  "first-value"
                                                   :second-child "second-value"
                                                   :third-child  "third-value"}
                                   :second-parent {:fourth-child "fourth-value"}})

(def ^{:private true} simple-map {:400 "Bad request"
                                  :401 "Unauthorized"
                                  :403 "Forbidden"})

(deftest indicies-test
  (testing "indicies with nil? on (nil nil 1 nil)"
    (is (= (indicies nil? '(nil nil 1 nil)) '(0 1 3))))
  (testing "indicies with #(not (= nil %1)) on (nil 1 2 \"a\" nil)"
    (is (= (indicies #(not (= nil %1)) '(nil 1 2 "a" nil)) '(1 2 3)))))

(deftest get-key-by-value-test
  (testing "get-key-by-value with key 401 on {:400 \"Bad request\" :401 \"Unauthorized\" :402 \"Forbidden\"}"
    (is (= (get-key-by-value 400 simple-map) "Bad request")))
  (testing "get-key-by-value with a complex-map"
    [(is (= (get-key-by-value "first-parent" complex-map) {:first-child "first-value", :second-child "second-value", :third-child "third-value"}))
     (is (= (get-key-by-value "second-parent" complex-map) {:fourth-child "fourth-value"}))
     (is (= (get-key-by-value "third-parent" complex-map) nil))]))

(deftest map-to-query-string-test
  (testing "map-to-query-string with {:firstname \"bob\" :secondname \"jones\"}"
    (is (= (map-to-query-string {:firstname "bob" :secondname "jones"}) "firstname=bob&secondname=jones"))))

(deftest regional-proxy?-test
  (testing "regional-proxy? with all valid proxies and one invalid"
    [(is (regional-proxy? "europe"))
     (is (regional-proxy? "americas"))
     (is (regional-proxy? "asia"))
     (not (regional-proxy? "fail"))]))

(deftest region-codes-test
  (testing "region-codes returns expected"
    (is (= (region-codes) '("br1" "eun1" "euw1" "jp1" "kr" "la1" "la2" "na" "na1" "oc1" "pbe1" "ru" "tr1")))))

(deftest region-code?-test
  (testing "region-code? on 3 valid region codes and one invalid"
    [(is (region-code? "br1"))
     (is (region-code? "kr"))
     (is (region-code? "ru"))
     (not (region-code? "fail"))]))

(deftest subregions-test
  (testing "subregions for all valid proxies and an invalid proxy"
    [(is (= (subregions "europe") {:eun1 "Europe Nordic & East", :euw1 "Europe West", :tr1 "Turkey", :ru "Russia"}))
     (is (= (subregions "americas") {:br1 "Brazil",
                                     :la1 "Latin America North",
                                     :la2 "Latin America South",
                                     :na  "North America (older)",
                                     :na1 "North America"}))
     (is (= (subregions "asia") {:jp1 "Japan", :kr "Korea", :oc1 "Oceanic"}))
     (not (= (subregions "europe") nil))]))

(deftest region-to-proxy-test
  (testing "region-to-proxy for 3 valid regions and an invalid one"
    [(is (= (region-to-proxy "kr") "asia"))
     (is (= (region-to-proxy "la1") "americas"))
     (is (= (region-to-proxy "tr1") "europe"))
     (not (= (region-to-proxy "fail") nil))]))