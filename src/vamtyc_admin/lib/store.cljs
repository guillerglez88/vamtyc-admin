(ns vamtyc-admin.lib.store
  (:require
   [lambdaisland.fetch :as fetch]))

(def base-url "http://localhost:3000")

(defn search [url]
  (-> (str base-url url)
      (fetch/request {:method "GET"
                      :headers {"Accept" "application/json"}})
      (.then #(-> % :body (js->clj :keywordize-keys true)))))

(defn read [url]
  (-> (str base-url url)
      (fetch/request {:method "GET"
                      :headers {"Accept" "application/json"}})
      (.then #(-> % :body (js->clj :keywordize-keys true)))))
