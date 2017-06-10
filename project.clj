(defproject clj-discord "0.1.1-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "2.3.0"]
                 [org.clojure/data.json "0.2.6"]
                 [forecast-clojure "1.0.3"]
                 [stylefruits/gniazdo "1.0.0"]]
  :main clj-discord.core
  :profiles {:uberjar {:aot :all}}
)
