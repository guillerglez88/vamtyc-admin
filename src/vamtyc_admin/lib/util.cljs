(ns vamtyc-admin.lib.util
  (:require
   [goog.string :as gstring]
   [clojure.string :as str]))

(def html-nbsp (gstring/unescapeEntities "&nbsp;"))

(defn padding [text count pad-str]
  (->> (. text -length)
       (- count)
       (#(repeat % pad-str))
       (apply str)
       (str text)))

(defn multiline-url [url]
  (let [[path query] (str/split url #"[?]")]
    (->> (str/split query #"[&]")
         (str/join "\n  &")
         (str path "\n  ?"))))

(defn inline-text [text]
  (->> (str/split-lines text)
       (map str/trim)
       (str/join)))

(defn hot-key? [evt & spec]
  (->> (apply hash-map spec)
       (filter (fn [[k v]] (= v (aget evt (name k)))))
       (count)
       (= (/ (count spec) 2))))
