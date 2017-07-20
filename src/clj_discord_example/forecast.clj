(ns clj-discord-example.forecast
  (:use [clojure.string :only [join]]
        [environ.core]
        [clojure.string :as str])
  (:require [cheshire.core :as json]
            [clj-http.client :as client]))

(def secret "e0971c18e4fe3fa0bf9cbb286be291b6")
(defn forecast
  "Retrieve the forecast for a given latitude and longitude"
  [lat lon & {:keys [params time]}]
  (let [base-url "https://api.darksky.net/forecast"
        api-key secret ;(env :forecast-key)
        url (join "/" [base-url api-key (join "," (filter not-empty (map str [lat lon time])))])
        response (client/get url {:query-params params :throw-exceptions false})]
    (cond (= 200 (:status response))
          (json/parse-string (:body response) true))))

(defn forecaster [lat lon & {:keys [params time]}]
  (let [forecast-result (forecast lat lon)]
    (:humidity (:currently forecast-result))))
