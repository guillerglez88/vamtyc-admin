(ns vamtyc-admin.components.resource)

(defn resource [res]
  [:div
   [:h3 (-> res :type name)]
   [:p (-> res :name)]])
