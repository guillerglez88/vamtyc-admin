(ns vamtyc-admin.component.editor
  (:require
   [vamtyc-admin.component.resources.lib.table :as table]))

(defn render [res]
  [:section
   [:header
    [:span {:class "keyword"}
     (:url res)]
    [:nav
     [:a {:class "keyword kw-3"} "JSON"]
     [:a {:class "keyword kw-3"} "YAML"]
     [:a {:class "keyword kw-3"} "TABLE"]]]
   [:section
    (when res
      (table/render res))]
   [:footer]])
