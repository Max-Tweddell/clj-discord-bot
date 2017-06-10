(ns clj-discord-example.core
  (:gen-class)
  (:require [clj-discord.core :as discord]
            [clj-discord-example.forecast :refer :all]
            [clj-http.client :as client]
            [clojure.string :as str]
            ))

(defonce token (.trim (slurp "token.txt")))

(defn d100 [type data]
  (discord/answer-command data "!d100" (str "Here you are a random number between 1 and 100: " (+ (rand-int 100) 1))))
(defn d20 [type data]
  (discord/answer-command data "!d20" (str "Here you are, a random number between 1 and 10 " (+ (rand-int 20) 1))))

(defn command-test [type data]
  (let [command (get data "content")]
    (discord/answer-command data "!blop" (str "blop " (str command)))
    ))
(defn weather [type data]
  (let [command (get data "content")]
    (discord/answer-command data "!weather" (str  "hi " (:humidity (:currently (forecast "37" "22")))))))

(defn log-event [type data] 
  (println "\nReceived: " type " -> " data))

(defn -main [& args]
  (discord/connect token 
                   {"MESSAGE_CREATE" [d20 d100 weather command-test]
                    "MESSAGE_UPDATE" [d20 d100 weather command-test]
                    ; "ALL_OTHER" [log-event]
                    }
                   true))

;(discord/disconnect)
