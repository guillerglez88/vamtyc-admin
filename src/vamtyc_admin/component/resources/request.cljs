(ns vamtyc-admin.component.resources.request
  (:require
   [reagent.core :as r]))

(defn request [res]
  (let [url (r/atom (:url res))
        set-url #(reset! url (-> % .-target .-value))]
    (fn [res attrs]
      [:form {:action @url
              :method (:method res)}
       [:input (merge {:class "input"
                       :type "text"
                       :default-value @url
                       :on-change set-url}
                      attrs)]])))
