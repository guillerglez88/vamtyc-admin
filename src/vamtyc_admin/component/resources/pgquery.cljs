(ns vamtyc-admin.component.resources.pgquery)

(defn list-item [_lookup pgquery _attrs]
  [:div {:class "pgquery"}
   [:span {:class "icon"}
    [:i {:class "fas fa-clipboard-question"}]]
   [:section
    [:header
     [:span {:class "keyword"}
      (:url pgquery)]]
    [:p
     [:span {:class "keyword kw-1"}
      (:expanded pgquery)]]]])

(def mode-displays
  {:list-item list-item})

(defn render [lookup route attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup route attrs]))
