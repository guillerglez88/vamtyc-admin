(ns vamtyc-admin.component.codings.core
  (:require
   [reagent.core :as r]
   [vamtyc-admin.lib.store :as store]))

(def code-descs (r/atom {}))

(defn fetch-coding [code]
  (-> (store/search code)
      (.then #(->> (:codes %)
                   (reduce (fn [acc curr] (assoc acc (:code curr) (:desc curr))) @code-descs)
                   (reset! code-descs)))))

(defn description [code]
  (when (->> code (contains? @code-descs) not)
    (fetch-coding code))
  [:span
   (-> @code-descs (get code) (or ""))])

(def mode-displays
  {:desc (fn [_l code _a] (description code))})

(defn render [lookup res attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup res attrs]))
