(ns vamtyc-admin.component.resources.route
  (:require
   [clojure.string :as str]
   [vamtyc-admin.component.coding :as coding]))

(defn str-method [method]
  (-> (or method :*)
      (name)
      (str/upper-case)))

(defn str-path [path]
  (->> (or path [])
       (map (fn [cmp] (or (:value cmp) (str ":" (:name cmp)))))
       (str/join "/")
       (str "/")))

(defn list-item [_lookup route _attrs]
  [:div {:class "route"}
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-route"}]]
   [:section
    [:header
     [:span {:class "keyword"}
      (:url route)]]
    [:p {:class "str-route"}
     [:span {:class "method keyword kw-1"
             :title "HTTP method"}
      (-> route :method str-method)]
     [:span {:class "keyword kw-1"
             :title "Route path"}
      (-> route :path str-path)]]
    [:p
     [coding/description (:code route)]]]])

(def mode-displays
  {:list-item list-item})

(defn render [lookup route attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup route attrs]))
