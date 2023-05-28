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
    (for [[field value] (seq res)]
      [:tr
       [:td
        field]
       [:td
        value]])]])
