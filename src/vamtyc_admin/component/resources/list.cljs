(ns vamtyc-admin.component.resources.list
  (:require
   [reagent.core :as r]))

(defn render [_lookup list _attrs]
  (let [url (r/atom (:url list))
        items (r/atom (:items list))
        set-url #(reset! url (-> % .-target .-value))]
    (fn [lookup _list attrs]
      [:section {:class "list"}
       [:header
        [:form {:action @url :method :GET}
         [:input {:type "text"
                  :name "url"
                  :default-value @url
                  :on-change set-url
                  :placeholder (:placeholder attrs)}]]]
       [:section {:class "items"}
        (for [item @items]
          (let [list-item (-> item :type lookup)]
            ^{:key (:id item)} [list-item item]))]])))
