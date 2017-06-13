(ns blitzcrank.constants)

(def error-code-map {:400 "Bad request"
                     :401 "Unauthorized"
                     :403 "Forbidden"
                     :404 "Data not found"
                     :405 "Method not allowed"
                     :415 "Unsupported media type"
                     :429 "Rate limit exceeded"
                     :500 "Internal server error"
                     :502 "Bad gateway"
                     :503 "Service unavailable"
                     :504 "Gateway timeout"})

(def region-map {:americas {:br1 "Brazil"
                            :la1 "Latin America North"
                            :la2 "Latin America South"
                            :na  "North America (older)"
                            :na1 "North America"}
                 :europe   {:eun1 "Europe Nordic & East"
                            :euw1 "Europe West"
                            :tr1  "Turkey"
                            :ru   "Russia"}
                 :asia     {:jp1 "Japan"
                            :kr  "Korea"
                            :oc1 "Oceanic"}
                 :pbe      {:pbe1 "PBE"}})