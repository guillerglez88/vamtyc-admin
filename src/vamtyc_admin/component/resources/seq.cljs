(ns vamtyc-admin.component.resources.seq)

(defn list-item [_lookup seq _attrs]
  [:div
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-arrow-down-1-9"}]]
   (:desc seq)])

(def mode-displays
  {:list-item list-item})

(defn render [lookup seq attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup seq attrs]))
