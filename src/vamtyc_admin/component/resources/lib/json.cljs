(ns vamtyc-admin.component.resources.lib.json)

(def meta-props #{:etag :created :modified :url})

(defn pstr [obj]
  (.stringify js/JSON obj nil 2))

(defn render [res]
  (->> (keys res)
       (filter #(not (meta-props %)))
       (select-keys res)
       (clj->js)
       (pstr)
       (vector :pre)))
