(ns clj-discord-example.core
  (:gen-class)
  (:require [clj-discord.core :as discord]
            [clj-discord-example.repl :as repl]
            [clj-discord-example.forecast :refer :all]
            [clj-http.client :as client]
            [cheshire.core :as json]
            [clj-discord-example.db :refer [db]]
            [clj-discord-example.db.messages :as messages] 
            ))

(defonce token (.trim (slurp "token.txt")))

(defn d100 [type data]
  (discord/answer-command data "!d100" (str "Here you are a random number between 1 and 100: " (+ (rand-int 100) 1))))
(defn d20 [type data]
  (discord/answer-command data "!d20" (str "Here you are, a random number between 1 and 20 " (+ (rand-int 20) 1))))

(defn command-test [type data]
  (let [command (get data "content")]
    (discord/answer-command data "!blop" (str "blop " (str command)))
    ))
(defn weather [type data]
  (let [command (get data "content")]
    (discord/answer-command data "!humidity" (str  "hi " (:humidity (:currently (forecast "37" "22")))))))

(defn void [type data]
  (let [server (get data "channel_id")]
    (if (= server "324776471883415552")
      (discord/delete-message data)
      )
    )
  )

(defn log-event [type data]
  (do
    (println "\nReceived: " type " -> " data)
    (messages/insert-message db {:message (json/generate-string data)})
    ))


(defn -main [& args]
  (do
    (discord/connect token 
                     {"MESSAGE_CREATE" [d20 d100 weather command-test void repl/repl-command]
                      "MESSAGE_UPDATE" [d20 d100 weather command-test void ]
                      ;;"ALL_OTHER" [log-event]
                      }
                     true))
  )

;(discord/disconnect)
