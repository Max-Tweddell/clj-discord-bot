(ns clj-discord-example.db.core
  (:require [clojure.java.jdbc :as jdbc]
            [java-jdbc.ddl :as sql]))

(def pg-db {:dbtype "postgresql"
            :dbname "jsontest"
            :host "localhost"
            :user "maxtweddell"
            :password "potato"
            :ssl false })

(defn save-message [type data]
  (j/query pg-db
           (sql/insert )

  )
