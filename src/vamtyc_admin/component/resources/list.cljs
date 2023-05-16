(ns vamtyc-admin.component.resources.list
  (:require
   [reagent.core :as r]))

(defn render [lookup list]
  (let [url (r/atom (:url list))
        set-url #(reset! url (-> % .-target .-value))]
    (fn [_lookup list attrs]
      [:section {:class "list"}
       [:header
        [:form {:action @url :method (:method list)}]
        [:input {:class "input"
                 :type "text"
                 :default-value @url
                 :on-change set-url
                 :placeholder (:placeholder attrs)}]]
       [:section {:class "items"}
        (for [item (:items list)]
          (let [list-item (-> item :type lookup)]
            ^{:key (:id item)} [list-item item]))]])))
