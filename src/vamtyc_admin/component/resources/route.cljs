(ns vamtyc-admin.component.resources.route
  (:require
   [clojure.string :as str]
   [vamtyc-admin.lib.util :as util]))

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
   [:div {:class "str-route"}
    [:span {:class "badge"}
     (-> route :method str-method (util/padding 6 util/html-nbsp))]
    [:span
     (-> route :path str-path)]]])

(def mode-displays
  {:list-item list-item})

(defn render [lookup route attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup route attrs]))
