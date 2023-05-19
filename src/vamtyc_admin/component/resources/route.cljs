(ns vamtyc-admin.component.resources.route
  (:require
   [clojure.string :as str]
   [goog.string :as gstring]))

(defn str-route [route]
  (let [method (-> route :method (or :*) name str/upper-case)
        padding (apply str (repeat (- 6 (. method -length)) (gstring/unescapeEntities "&nbsp;")))]
    (->> (or (:path route) [])
         (map (fn [cmp] (or (:value cmp) (str ":" (:name cmp)))))
         (str/join "/")
         (str method padding " /"))))

(defn list-item [_lookup route _attrs]
  [:div {:class "route"}
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-route"}]]
   [:span {:class "str-route"}
    (str-route route)]])

(def mode-displays
  {:list-item list-item})

(defn render [lookup route attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup route attrs]))
