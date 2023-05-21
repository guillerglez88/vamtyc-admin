(ns vamtyc-admin.component.resources.seq)

(defn list-item [_lookup seq _attrs]
  [:div {:class "seq"}
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-arrow-down-1-9"}]]
   [:section
    [:header
     [:span {:class "keyword"}
      (:url seq)]]
    [:p
     (:desc seq)]]])

(def mode-displays
  {:list-item list-item})

(defn render [lookup seq attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup seq attrs]))
