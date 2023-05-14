(ns vamtyc-admin.component.resources.resource)

(defn render [_lookup res]
  [:div {:class "panel-block"}
   [:span {:class "panel-icon"}
    [:i {:class "fa-solid fa-box"}]]
   (:desc res)])
