(ns user
  (:require [mount.core :as mount]
            clj-discord-bot.core))

(defn start []
  (mount/start-without #'clj-discord-bot.core/repl-server))

(defn stop []
  (mount/stop-except #'clj-discord-bot.core/repl-server))

(defn restart []
  (stop)
  (start))


