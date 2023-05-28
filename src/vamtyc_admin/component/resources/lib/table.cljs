(ns vamtyc-admin.component.resources.lib.table)

(defn render [res]
  [:table
   [:thead
    [:tr
     [:th
      "field"]
     [:th
      "value"]]]
   [:tbody
    (for [[field value index] (->> (seq res) (map-indexed #(conj %2 %1)))]
      ^{:key index}
      [:tr
       [:td
        field]
       [:td
        value]])]])
