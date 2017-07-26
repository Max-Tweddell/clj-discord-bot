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
<<<<<<< HEAD

(defn getRandomMessage [type data]
  (let [commmand (get data "content" )]
   (discord/answer-command data "random" (str (messages/random-message db)))))
(defn void [type data]
  (let [server (get data "channel_id")]
    (if (= server "324776471883415552")
      (discord/delete-message data))))

(defn getRandomNumber [type data]
  (let [command (get data "content")]
    (discord/answer-command data "getRandomNumber()" (str "Here you are, a random number : " 4))))
(defn rot13 [in]
  (let [A (into #{} "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
        Am (->> (cycle A) (drop 26) (take 52) (zipmap A))]
    (apply str (map #(Am % %) in))))

(defn encrypt [type data]
  (let [command (get data "content") args (str/join " " (rest (str/split (get data "content") #" ")))]
    (discord/answer-command data "encrypt" (rot13 args))))
(defn decrypt [type data]
  (let [command (get data "content") args (str/join " " (rest (str/split (get data "content") #" ")))]
    (try (discord/answer-command data "decrypt" (str args "->" (rot13 args))) (catch Exception e (println "args")))))

(defn mum [type data]
=======
(defn weather [type data]
>>>>>>> bb4291c7b0c9cb7a6e076b5de18e849fbbd7e483
  (let [command (get data "content")]
    (discord/answer-command data "!weather" (str  "hi " (:humidity (:currently (forecast "37" "22")))))))

<<<<<<< HEAD
(defn repler [type data]
  (let [command (get data "content") args (str/join " " (rest (str/split (get data "content") #" ")))]
    (discord/answer-command data "eval" (try  (eval (read-string args)) (catch Exception e (println "uh oh") (discord/answer-command data "error"))))))

(defn -main [& args]
  (discord/connect token {"MESSAGE_UPDATE" [d20 d100 weather command-test void log-event]
                          "MESSAGE_CREATE" [d20 d100 weather command-test void getRandomNumber mum log-event oaky repler encrypt decrypt getRandomMessage]} true))
=======
(defn log-event [type data] 
  (println "\nReceived: " type " -> " data))

(defn -main [& args]
  (discord/connect token 
                   {"MESSAGE_CREATE" [d20 d100 weather command-test]
                    "MESSAGE_UPDATE" [d20 d100 weather command-test]
                    ; "ALL_OTHER" [log-event]
                    }
                   true))
>>>>>>> bb4291c7b0c9cb7a6e076b5de18e849fbbd7e483

                                        ;(discord/disconnect)
