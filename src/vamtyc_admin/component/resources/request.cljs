(ns vamtyc-admin.component.resources.request
  (:require
   [reagent.core :as r]))

(defn render [_lookup res]
  (let [url (r/atom (:url res))
        set-url #(reset! url (-> % .-target .-value))]
    (fn [_lookup res attrs]
      [:div {:class "wkr-request"}
       [:form {:action @url
               :method (:method res)}]
       [:input (merge {:class "input wkr-request-url"
                       :type "text"
                       :default-value @url
                       :on-change set-url}
                      attrs)]])))
