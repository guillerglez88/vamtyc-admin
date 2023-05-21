(ns vamtyc-admin.component.coding
  (:require
   [reagent.core :as r]
   [vamtyc-admin.lib.store :as store]
   [goog.uri.utils :as guri]))

(def code-descs (r/atom {}))

(defn fetch-coding [code]
  (-> (store/search code)
      (.then #(->> (:codes %)
                   (reduce (fn [acc curr] (assoc acc (:code curr) (:desc curr))) @code-descs)
                   (reset! code-descs)))))

(defn list-item
  ([_lookup coding _attrs]
   (list-item coding))
  ([coding]
   [:div {:class "coding"}
    [:span {:class "icon"}
     [:i {:class "fas fa-tag"}]]
    [:section
     [:header
      [:span {:class "keyword"}
       (:url coding)]]
     [:p
      (for [code (-> coding :codes (or []))]
        (let [ccode (-> code :code (guri/getParamValue "code"))]
          ^{:key ccode}
          [:span {:class "keyword kw-1"
                  :title (:desc code)}
           ccode]))]]]))


(defn description
  ([_lookup code _attrs]
   (description code))
  ([code]
   (when (->> code (contains? @code-descs) not)
     (fetch-coding code))
   [:span
    (-> @code-descs (get code) (or ""))]))

(def mode-displays
  {:desc      description
   :list-item list-item})

(defn render [lookup res attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup res attrs]))
