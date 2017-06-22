(defproject blitzcrank "0.1.3"
  :description "Clojure version of the League of Legends API"
  :url "https://github.com/elken/blitzcrank"
  :license {:name "BSD 3 Clause"
            :url  "https://opensource.org/licenses/BSD-3-Clause"}
  :plugins [[lein-codox "0.10.3"]
            [lein-cloverage "1.0.9"]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.incubator "0.1.4"]
                 [yogthos/config "0.8"]
                 [slingshot "0.12.2"]
                 [clj-http "3.6.1"]
                 [cheshire "5.7.1"]]
  :codox
  {:output-path "docs"
   :metadata    {:doc/format :markdown}
   :source-uri  "http://github.com/elken/blitzcrank/blob/master/{filepath}#L{line}"})
