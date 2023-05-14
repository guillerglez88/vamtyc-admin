(ns vamtyc-admin.component.resources.list)

(defn render [lookup res]
  [:div
   (for [item (:items res)]
     (let [render-item (-> item :code lookup)]
       ^{:key (:id item)} [render-item item]))])
