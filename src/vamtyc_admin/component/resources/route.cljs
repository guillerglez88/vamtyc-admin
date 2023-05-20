(ns vamtyc-admin.component.resources.route
  (:require
   [clojure.string :as str]
   [goog.string :as gstring]))

(def html-nbsp (gstring/unescapeEntities "&nbsp;"))

(defn padding [text count pad-str]
  (->> (. text -length)
       (- count)
       (#(repeat % pad-str))
       (apply str)
       (str text)))

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
     (-> route :method str-method (padding 6 html-nbsp))]
    [:span
     (-> route :path str-path)]]])

(def mode-displays
  {:list-item list-item})

(defn render [lookup route attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup route attrs]))
