(ns vamtyc-admin.component.editor
  (:require
   [reagent.core :as r]
   [vamtyc-admin.component.resources.lib.table :as table]
   [vamtyc-admin.component.resources.lib.json :as json]))

(def wkf-table  "/Coding/data-format?code=table")
(def wkf-json   "/Coding/data-format?code=json")

(defn select-format
  ([format to]
   (partial select-format format to))
  ([format to evt]
   (.preventDefault evt)
   (reset! format to)))

(defn render [_res]
  (let [format (r/atom wkf-json)]
    (fn [res]
      [:section
       [:header
        [:span {:class "keyword"}
         (:url res)]
        [:nav
         [:a {:class "keyword kw-3"
              :on-click (select-format format wkf-json)}
          (when (= wkf-json @format)
            [:i {:class  "fas fa-circle"}])
          "JSON"]
         [:a {:class "keyword kw-3"
              :on-click (select-format format wkf-table)}
          (when (= wkf-table @format)
            [:i {:class  "fas fa-circle"}])
          "TABLE"]]]
       [:section
        (when res
          (cond
            (= wkf-table @format) (table/render res)
            (= wkf-json @format) (json/render res)
            :else [:div]))]
       [:footer]])))
