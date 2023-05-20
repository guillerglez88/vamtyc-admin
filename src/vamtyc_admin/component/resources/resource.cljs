(ns vamtyc-admin.component.resources.resource)

(defn list-item [_lookup res _attrs]
  [:div
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-box"}]]
   [:section
    [:header
     [:span {:class "keyword"}
      (->> (res :of)
           (name)
           (str "/"))]]
    [:p
     (:desc res)]]])

(def mode-displays
  {:list-item list-item})

(defn render [lookup res attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup res attrs]))
