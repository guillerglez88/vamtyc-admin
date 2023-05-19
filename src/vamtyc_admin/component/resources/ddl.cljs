(ns vamtyc-admin.component.resources.ddl)

(defn list-item [_lookup ddl _attrs]
  [:div
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-database"}]]
   (:desc ddl)])

(def mode-displays
  {:list-item list-item})

(defn render [lookup ddl attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup ddl attrs]))
