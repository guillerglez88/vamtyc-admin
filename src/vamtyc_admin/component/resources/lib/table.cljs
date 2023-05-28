(ns vamtyc-admin.component.resources.lib.table
  (:require
   [vamtyc-admin.lib.util :as util]))

(defn render [res]
  [:table
   [:thead
    [:tr
     [:th
      "field"]
     [:th
      "value"]]]
   [:tbody
    (for [[field value index] (->> (util/flat-obj res) (map-indexed #(conj %2 %1)))]
      ^{:key index}
      [:tr
       [:td
        field]
       [:td
        value]])]])
