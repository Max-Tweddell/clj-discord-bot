(defproject clj-discord-example "0.1.0-SNAPSHOT"
  :dependencies [[clj-discord "0.1.1-SNAPSHOT"]
                 [cheshire "5.7.1"]
                 [org.clojure/java.jdbc "0.7.0-alpha3"]
                 [java-jdbc/dsl "0.1.3"]
                 [clj-http "3.6.1"]
                 [clojail "1.0.6"]
                 [org.clojure/tools.nrepl "0.2.13"]
                 [org.clojure/data.json "0.2.6"]
                 [com.layerware/hugsql "0.4.7"]
                 [org.postgresql/postgresql "9.4.1212"]
                 [environ "1.1.0"]]
  :plugins [[lein-kibit "0.1.5"] [lein-cljfmt "0.5.6"]]
  :main clj-discord-example.core
  :profiles {:uberjar
             {:aot [clj-discord-example.core]}}
  :user {:env {:forecast-key "e0971c18e4fe3fa0bf9cbb286be291b6"} })
