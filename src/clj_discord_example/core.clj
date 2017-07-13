(ns clj-discord-example.core
  (:gen-class)
  (:require [clj-discord.core :as discord]
            [clj-discord-example.repl :as repl]
            [clj-discord-example.forecast :refer :all]
            [clj-http.client :as client]
            [cheshire.core :as json]

            [clj-discord-example.db :refer [db]]
            [clj-discord-example.db.messages :as messages]

            [clojure.string :as str]))

(defonce token (.trim (slurp "token.txt")))

(defn d100 [type data]
  (discord/answer-command data "!d100" (str "Here you are a random number between 1 and 100: " (inc (rand-int 100)))))
(defn d20 [type data]
  (discord/answer-command data "!d20" (str "here u are a randem numbur bigger than 1 (one)  but littler than tweny sex: " (inc (rand-int 27)))))

(defn command-test [type data]
  (let [command (get data "content")]
    (discord/answer-command data "!blop" (str "blop " (str command)))))
(defn weather [type data]
  (let [command (get data "content")]
    (discord/answer-command data "!humidity" (str  "hi " (:humidity (:currently (forecast "37" "22")))))))

(defn lmgtfy [data command]
  (let [channel_id (get data "channel_id") message (get data "id")]
    (discord/answer-command data "lmgtfy" (str "http://lmgtfy.com/?q=darude+sandstorm" (str/replace command " " "+" )))
    ))

(defn void [type data]
  (let [server (get data "channel_id")]
    (if (= server "324776471883415552")
      (discord/delete-message data))))

(defn getRandomNumber [type data]
  (let [command (get data "content")]
    (discord/answer-command data "getRandomNumber()" (str "Here you are, a random number : " 4))))

(defn rot13 [type data]
  (let [command (get data "content")
        args (str/join " " (rest (str/split (get data "content") #" ")))]
    (discord/answer-command data "encrypt" (clojure.string/join (map char (map (fn [x] (- x 5)) (map int args)))))))
(defn mum [type data]
  (let [command (get data "content")]
    (discord/answer-message data "mom " "mum")))
(defn oaky [type data]
  (let [commmand (get data "content")]
    (discord/answer-message data "oaky"  "https://68.media.tumblr.com/15208297a50932cccbef51a5dbeb47bf/tumblr_inline_ojfxuvC1RV1qierqv_540.jpg")))
(defn log-event [type data]
  (do
    (println "\nReceived: " type " -> " data)
    (messages/insert-message db {:message (json/generate-string data)})))

(defn repler [type data]
  (let [command (get data "content") args (str/join " " (rest (str/split (get data "content") #" ")))]
    (discord/answer-command data "eval" (try  (eval (read-string args)) (catch Exception e (println "uh oh"))))))

(defn -main [& args]
  (discord/connect token {"MESSAGE_UPDATE" [d20 d100 weather command-test void log-event], "MESSAGE_CREATE" [d20 d100 weather command-test void getRandomNumber mum log-event oaky repler rot13]} true))

;(discord/disconnect)
