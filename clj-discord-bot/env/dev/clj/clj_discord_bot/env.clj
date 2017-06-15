(ns clj-discord-bot.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [clj-discord-bot.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[clj-discord-bot started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[clj-discord-bot has shut down successfully]=-"))
   :middleware wrap-dev})
