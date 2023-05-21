(ns vamtyc-admin.component.resources.core
  (:require
   [vamtyc-admin.component.coding :as coding]
   [vamtyc-admin.component.resources.ddl :as ddl]
   [vamtyc-admin.component.resources.list :as lst]
   [vamtyc-admin.component.resources.pgquery :as pgq]
   [vamtyc-admin.component.resources.queryp :as queryp]
   [vamtyc-admin.component.resources.resource :as res]
   [vamtyc-admin.component.resources.route :as route]
   [vamtyc-admin.component.resources.seq :as seq]))

(def component-map
  {:Ddl       ddl/render
   :Resource  res/render
   :Route     route/render
   :Queryp    queryp/render
   :List      lst/render
   :Seq       seq/render
   :Coding    coding/render
   :PgQuery   pgq/render})

(defn not-implemented [_lookup res _attrs]
  [:div
   [:span {:class "icon"}
    [:i {:class "fas fa-question"}]]
   [:section
    [:header
     [:span {:class "keyword"}
      (:url res)]]
    [:p]]])

(defn lookup [type]
  (let [type-kw (keyword type)]
    (-> component-map type-kw (or not-implemented) (partial lookup))))
