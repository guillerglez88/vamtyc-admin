(ns vamtyc-admin.lib.util
  (:require
   [goog.string :as gstring]
   [clojure.string :as str]))

(def html-nbsp (gstring/unescapeEntities "&nbsp;"))
(def meta-props #{:type :id :etag :created :modified :url})

(defn meta-prop? [key]
  (-> key keyword meta-props not not))

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

(defn mapseg
  ([items transform]
   (mapseg [] (first items) (-> items rest vec) transform))
  ([head item tail transform]
   (if (empty? tail)
     [[head item tail]]
     (->> (mapseg (conj head (transform item))
                  (first tail)
                  (-> tail rest vec)
                  transform)
          (concat [[head item tail]])))))

(defn flat-obj
  ([res]
   (flat-obj "" res))
  ([key val]
   (cond
     (vector? val)  (->> (identity val)
                         (mapcat #(flat-obj key %))
                         (vec))
     (map? val)     (->> (seq val)
                         (mapcat (fn [[k v]]
                                   (-> (if (= "" key) "" (str key "."))
                                       (str (name k))
                                       (flat-obj v))))
                         (vec))
     :else          (vector [key val]))))
