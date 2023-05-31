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
       (when res
         [:header
          [:div
           [:span {:class "keyword mb-2"}
            (:url res)]
           [:p
            [:small {:title "etag"}
             [:i {:class "fas fa-hashtag"}]
             (:etag res)]
            [:small {:title "created"}
             [:i {:class "fas fa-square-plus"}]
             (:created res)]
            [:small {:title "modified"}
             [:i {:class "fas fa-pen-to-square"}]
             (:modified res)]]]
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
            "TABLE"]]])
       [:section
        (when res
          (cond
            (= wkf-table @format) (table/render res)
            (= wkf-json @format) (json/render res)
            :else [:div]))]
       [:footer]])))
