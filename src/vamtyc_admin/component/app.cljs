(ns vamtyc-admin.component.app
  (:require
   [vamtyc-admin.component.lib.panel :refer [panel]]
   [vamtyc-admin.component.resources.request :refer [request]]))

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
    [panel
     [request (:filter res) {:placeholder "search"}]
     [app-main (:main res)]]]])
