(ns clj-discord-example.db
 (:require [clojure.java.jdbc :as j]) )

(let [db-host "localhost"
      db-port 5432
      db-name "jsontest"]

  (def pgdb {:classname "org.postgresql.Driver" ; must be in classpath
           :subprotocol "postgresql"
           :subname (str "//" db-host ":" db-port "/" db-name)
                                        ; Any additional keys are passed to the driver
                                        ; as driver-specific properties.
           :user "maxtweddell"
           :password "potato"}))


(defn insert-message [message]
  (j/db-do-commands pgdb (str "insert into test1 (info) VALUES" message ");")))
