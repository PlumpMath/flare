(ns flare.congress
  (:require [flare.core :as flare]))

(def *base-url* "http://congress.api.sunlightfoundation.com")

(defn GET
  [route]
  (flare/GET *base-url* route))

(defn legislators
  [{:keys [bioguide-id
           birthday
           chamber
           crp-id
           district
           in-office
           fec-ids
           first-name
           gender
           govtrack-id
           last-name
           lis-id
           middle-name
           name-suffix
           nickname
           party
           per-page
           query
           senate-class
           state
           state-name
           state-rank
           term-start
           term-end
           thomas-id
           title
           votesmart-id] :as fields}]
  (if query
    (GET (str "/legislators?query=" query))
    (GET (str "/legislators?" (flare/parameterize-fields fields)))))

(defn locate-legislators
  ([latitude longitude]
     (GET (str "/legislators/locate?"
               "latitude="   latitude
               "&longitude=" longitude)))
  ([zip]
     (GET (str "/legislators/locate?"
               "zip=" zip))))

(defn locate-districts
  ([latitude longitude]
     (GET (str "/districts/locate?"
               "latitude="   latitude
               "&longitude=" longitude)))
  ([zip]
     (GET (str "/districts/locate?"
               "zip=" zip))))

(defn committees
  [{:keys [committee-id
           chamber
           subcommittee
           member-ids
           parent-committee-id] :as fields}]
  (GET (str "/committees?" (flare/parameterize-fields fields))))

(defn bills
  [{:keys [bill-id
           bill-type
           number
           congress
           chamber
           introduced-on
           last-action-at
           last-vote-at
           last-version-on
           query] :as fields}]
  (GET (str "/bills?" (flare/parameterize-fields fields))))

(defn votes
  [{:keys [roll-id
           chamber
           number
           year
           congress
           voted-at
           vote-type
           roll-type
           query
           question
           required
           result
           bill-id] :as fields}]
  (GET (str "/votes?" (flare/parameterize-fields fields))))

(defn floor-updates
  [{:keys [chamber
           timestamp
           congress
           legislative-day
           year
           bill-ids
           roll-ids
           legislator-ids] :as fields}]
  (GET (str "/floor_updates?" (flare/parameterize-fields fields))))

(defn hearings
  [{:keys [committee-id
           occurs-at
           congress
           chamber
           dc
           bill-ids
           hearing-type] :as fields}]
  (GET (str "/hearings?" (flare/parameterize-fields fields))))

(defn upcoming-bills
  [{:keys [legislative-day
           range
           conress
           chamber
           source-type
           bill-id] :as fields}]
  (GET (str "/upcoming_bills?" (flare/parameterize-fields fields))))
