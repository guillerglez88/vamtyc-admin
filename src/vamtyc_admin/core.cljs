(ns ^:figwheel-hooks vamtyc-admin.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]
   [vamtyc-admin.component.app :refer [app]]))

(def vamtyc-admin-res
  {:version "0.0.1-alpha-1"
   :behaviour [{:code "/Coding/vamtyc-admin-behavour?code=list-search"
                :desc "Allow main list search"
                :target ["main"]}]
   :main {:type "List",
          :url "/List?_of=Queryp&_limit=2&_offset=0",
          :items [],
          :total 0}})

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn mount [el]
  (rdom/render [app vamtyc-admin-res] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element))
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)

