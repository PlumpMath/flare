(ns flare.core
  (:require [clojure.string  :as str]
            [clj-http.client :as client]
            [cheshire.core   :refer :all]))

(def *api-key* "<api key>")

(defn GET
  [base-url route]
  (->> (client/get (str base-url route) {:headers {"X-APIKEY" *api-key*}})
       :body
       parse-string))

(defn snake-case
  [s]
  (str/replace s "-" "_"))

(defn parameterize-fields
  [fields]
  (str/join "&" (map #(str (snake-case (name %)) "=" (fields %))
                     (keys fields))))
