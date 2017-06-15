-- src/clojure-discord-example/db/sql/messages.sql


-- :name insert-message :! :n
-- :doc Insert a single character
insert into messages (info)
values (:message)
--WITHOUT FUNCTION AS IMPLICIT
