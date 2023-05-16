(ns vamtyc-admin.component.resources.core
  (:require
   [vamtyc-admin.component.resources.resource :as res]
   [vamtyc-admin.component.resources.list :as lst]
   [vamtyc-admin.component.resources.request :as req]))

(defn not-supported [_lookup _res]
  [:div
    :p "not supported"])

(def component-map {:Ddl       not-supported
                    :Resource  res/render
                    :Route     not-supported
                    :Queryp    not-supported
                    :List      lst/render
                    :Seq       not-supported
                    :Etag      not-supported
                    :Request   req/render})

(defn lookup [type]
  (let [type-kw (keyword type)]
    (-> component-map type-kw (partial lookup))))
