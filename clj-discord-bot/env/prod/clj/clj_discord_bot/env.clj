(ns clj-discord-bot.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[clj-discord-bot started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[clj-discord-bot has shut down successfully]=-"))
   :middleware identity})
