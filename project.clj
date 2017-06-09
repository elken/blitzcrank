(defproject lol-api "0.1.0-SNAPSHOT"
  :description "Clojure version of the League of Legends API"
  :url "https://elken.github.io/lol-api"
  :license {:name "BSD 3 Clause"
            :url "https://opensource.org/licenses/BSD-3-Clause"}
  :plugins [[lein-codox "0.10.3"]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [yogthos/config "0.8"]]
  :codox
  {:output-path "../docs"
   :metadata {:doc/format :markdown}
   :source-uri "http://github.com/elken/lol-api/blob/master/{filepath}#L{line}"}
  :profiles {:dev  {:resource-paths ["config"]}})
