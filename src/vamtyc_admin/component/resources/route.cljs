(ns vamtyc-admin.component.resources.route)

(defn list-item [_lookup route _attrs]
  [:div
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-route"}]]
   (:name route)])

(def mode-displays
  {:list-item list-item})

(defn render [lookup route attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup route attrs]))
