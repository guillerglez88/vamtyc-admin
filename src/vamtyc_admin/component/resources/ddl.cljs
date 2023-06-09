(ns vamtyc-admin.component.resources.ddl)

(defn list-item [_lookup ddl _attrs]
  [:div {:class "ddl"}
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-database"}]]
   [:section
    [:header
     [:span {:class "keyword"}
      (:url ddl)]]
    [:p
     (:desc ddl)]]])

(def mode-displays
  {:list-item list-item})

(defn render [lookup ddl attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup ddl attrs]))
