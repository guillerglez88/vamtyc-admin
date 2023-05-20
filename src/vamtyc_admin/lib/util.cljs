(ns vamtyc-admin.lib.util
  (:require
   [goog.string :as gstring]))

(def html-nbsp (gstring/unescapeEntities "&nbsp;"))

(defn padding [text count pad-str]
  (->> (. text -length)
       (- count)
       (#(repeat % pad-str))
       (apply str)
       (str text)))
