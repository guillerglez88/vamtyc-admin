(ns vamtyc-admin.component.resources.lib.json
  (:require
   [vamtyc-admin.lib.util :as util]))

(defn pstr [obj]
  (.stringify js/JSON obj nil 2))

(defn render [res]
  (->> (keys res)
       (filter #(-> % util/meta-prop? not))
       (select-keys res)
       (clj->js)
       (pstr)
       (vector :pre)))
