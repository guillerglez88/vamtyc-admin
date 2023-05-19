(ns vamtyc-admin.component.resources.queryp)

(defn list-item [_lookup queryp _attrs]
  [:div
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-filter"}]]
   (:desc queryp)])

(def mode-displays
  {:list-item list-item})

(defn render [lookup queryp attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup queryp attrs]))
