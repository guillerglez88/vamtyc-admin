(ns vamtyc-admin.component.app
  (:require
   [vamtyc-admin.component.lib.core :as lib]))

(defn app-main-item [res]
  [:div {:class "panel-block"}
   [:span {:class "panel-icon"}
    [:i {:class "fa-solid fa-box"}]]
   (:desc res)])

(defn app-main [list]
  [:div
    (for [item (:items list)]
      ^{:key (:id item)} [app-main-item item])])

(defn app-filter [filter]
  [:form {:action (:url filter)
          :method (:method filter)}
   [:input {:class "input"
            :type "text"
            :placeholder "search"
            :default-value (:url filter)}]])

(defn app [res]
  [:section {:class "section"}
   [:div {:class "container"}
    [lib/panel
     [app-filter (:filter res)]
     [app-main (:main res)]]]])
