(ns ^:figwheel-hooks vamtyc-admin.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]
   [vamtyc-admin.components.core :as cmp]))

(def vamtyc-admin-index
  {:version "0.0.1-alpha-1"
   :behavior [{:code "/Coding/vamtyc-admin-behaviour?code=trigger"
               :on "/Coding/vamtyc-admin-behaviour-event?code=load"
               :action "/Coding/vamtyc-admin-behaviour-action?code=xreq"
               :target ["flter"]
               :replace ["main"]}
              {:code "/Coding/vamtyc-admin-behaviour?code=override"
               :on "/Coding/vamtyc-admin-behaviour-event?code=navigate"
               :action "/Coding/vamtyc-admin-behaviour-action?code=xreq"
               :target ["main"]}]
   :filter {:type :Request
            :method :GET
            :url "/List?_of=Resource"}
   :main {:type :List
          :code "/Coding/wellknown-resources?code=list"
          :url "/List?_of=Resource"
          :items []
          :total 0}})

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn mount [el]
  (rdom/render [cmp/app] el))

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

