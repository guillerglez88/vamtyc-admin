(ns vamtyc-admin.component.resources.resource)

(defn resource [res]
  [:div
   [:h3 (-> res :type name)]
   [:p (-> res :name)]])
