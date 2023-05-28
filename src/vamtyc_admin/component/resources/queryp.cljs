(ns vamtyc-admin.component.resources.queryp
  (:require
    [vamtyc-admin.component.coding :as coding]
    [vamtyc-admin.component.resources.lib.table :as table]))

(defn list-item [_lookup queryp _attrs]
  (let [qp-name (-> queryp :name name)
        of (-> queryp :of name)
        default (-> queryp :value (or "..."))]
    [:div {:class "queryp"}
     [:span {:class "icon"}
      [:i {:class "fa-solid fa-filter"}]]
     [:section
      [:header
       [:span {:class "keyword"}
        (:url queryp)]]
      [:p {:class "desc"}
       [:span
        (-> queryp :desc (str ". "))]
       [coding/description (:code queryp)]]
      [:p
       [:span {:class "keyword kw-1"}
        (str "/" of "?" qp-name "=" default)]]]]))

(defn table [_lookup queryp _attrs]
  [table/render queryp])

(def mode-displays
  {:list-item list-item
   :table     table})

(defn render [lookup queryp attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup queryp attrs]))
