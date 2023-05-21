(ns vamtyc-admin.component.resources.queryp
  (:require
    [vamtyc-admin.component.coding :as coding]))

(defn list-item [_lookup queryp _attrs]
  [:div {:class "queryp"}
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-filter"}]]
   [:section
    [:header {:class "keyword"}
     (-> queryp :name name)]
    [:p {:class "desc"}
     [:span
      (-> queryp :desc (str ". "))]
     [coding/description (:code queryp)]]]])

(def mode-displays
  {:list-item list-item})

(defn render [lookup queryp attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup queryp attrs]))
