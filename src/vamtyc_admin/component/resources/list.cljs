(ns vamtyc-admin.component.resources.list)

(defn render [lookup res]
  [:div
   (for [item (:items res)]
     (let [list-item (-> item :type lookup)]
       ^{:key (:id item)} [list-item item]))])
