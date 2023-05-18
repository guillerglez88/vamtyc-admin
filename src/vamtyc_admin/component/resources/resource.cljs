(ns vamtyc-admin.component.resources.resource)

(defn list-item [res]
  [:div
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-box"}]]
   (:desc res)])

(defn render [_lookup res]
  [list-item res])
