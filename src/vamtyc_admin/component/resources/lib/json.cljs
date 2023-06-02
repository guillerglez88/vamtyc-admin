(ns vamtyc-admin.component.resources.lib.json)

(def meta-props #{:type :id :etag :created :modified :url})

(defn pstr [obj]
  (.stringify js/JSON obj nil 2))

(defn render [res]
  (->> (keys res)
       (filter #(-> % keyword meta-props not))
       (select-keys res)
       (clj->js)
       (pstr)
       (vector :pre)))
