(ns vamtyc-admin.component.resources.lib.table
  (:require
   [vamtyc-admin.lib.util :as util]))

(def meta-props #{:type :id :etag :created :modified :url})

(defn render [res]
  [:table
   [:thead
    [:tr
     [:th
      "field"]
     [:th
      "value"]]]
   [:tbody
    (for [[field value index] (->> (util/flat-obj res)
                                   (filter #(-> % first util/meta-prop? not))
                                   (map-indexed #(conj %2 %1)))]
      ^{:key index}
      [:tr
       [:td
        field]
       [:td
        (str value)]])]])
