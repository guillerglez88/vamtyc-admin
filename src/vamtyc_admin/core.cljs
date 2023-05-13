(ns ^:figwheel-hooks vamtyc-admin.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]
   [vamtyc-admin.component.core :as cmp]))

(def vamtyc-admin-res
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
   :main {:type "List",
          :url "/List?_of=Resource&_limit=5",
          :items [{:routes "/List?_of=Route&res-type=Resource",
                   :desc "REST resource",
                   :type "Resource",
                   :etag "4",
                   :created "2023-05-06 16:29:38.920541",
                   :modified "2023-05-06 16:29:38.920541",
                   :status "/Coding/resource-statuses?code=active",
                   :id "resource",
                   :url "/Resource/resource",
                   :code "/Coding/wellknown-resources?code=resource",
                   :of "Resource"}
                  {:routes "/List?_of=Route&res-type=Ddl",
                   :desc "Postgres DDL resource",
                   :type "Resource",
                   :etag "5",
                   :created "2023-05-06 16:29:38.929937",
                   :modified "2023-05-06 16:29:38.929937",
                   :status "/Coding/resource-statuses?code=active",
                   :id "ddl",
                   :url "/Resource/ddl",
                   :code "/Coding/wellknown-resources?code=resource",
                   :of "Ddl"}
                  {:routes "/List?_of=Route&res-type=Seq",
                   :desc "Sequence resource",
                   :type "Resource",
                   :etag "6",
                   :created "2023-05-06 16:29:38.933943",
                   :modified "2023-05-06 16:29:38.933943",
                   :status "/Coding/resource-statuses?code=active",
                   :id "seq",
                   :url "/Resource/seq",
                   :code "/Coding/wellknown-resources?code=resource",
                   :of "Seq"}
                  {:routes "/List?_of=Route&res-type=PgQuery",
                   :desc "Postgres query",
                   :type "Resource",
                   :etag "7",
                   :created "2023-05-06 16:29:38.944093",
                   :modified "2023-05-06 16:29:38.944093",
                   :status "/Coding/resource-statuses?code=active",
                   :id "pgquery",
                   :url "/Resource/pgquery",
                   :code "/Coding/wellknown-resources?code=resource",
                   :of "PgQuery"}
                  {:routes "/List?_of=Route&res-type=Route",
                   :desc "REST route",
                   :type "Resource",
                   :etag "8",
                   :created "2023-05-06 16:29:38.954257",
                   :modified "2023-05-06 16:29:38.954257",
                   :status "/Coding/resource-statuses?code=active",
                   :id "route",
                   :url "/Resource/route",
                   :code "/Coding/wellknown-resources?code=resource",
                   :of "Route"}],
          :total 9,
          :nav {:first "/List?_of=Resource&_limit=5&_offset=0",
                :prev "/List?_of=Resource&_limit=5&_offset=0",
                :next "/List?_of=Resource&_limit=5&_offset=4",
                :last "/List?_of=Resource&_limit=5&_offset=4"},
          :pgquery "/PgQuery/984775997da7e5458215889ba8ff98457d2f5d0047a6e94860475581a5d39174"}})


;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn mount [el]
  (rdom/render [cmp/app vamtyc-admin-res] el))

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

