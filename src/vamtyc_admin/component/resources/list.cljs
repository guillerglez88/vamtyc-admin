(ns vamtyc-admin.component.resources.list
  (:require
   [reagent.core :as r]
   [lambdaisland.fetch :as fetch]
   [clojure.string :as str]))

(defn search [form]
  (let [url (-> form .-target .-action (str/replace-first (. js/window -location) ""))
        method (-> form .-target .-method)]
    (-> (str "http://localhost:3000/" url)
        (fetch/request {:method method
                        :headers {"Accept" "application/json"}})
        (.then #(-> % :body (js->clj :keywordize-keys true))))))

(defn render [_lookup list _attrs]
  (let [url (r/atom (:url list))
        items (r/atom (:items list))
        set-url #(reset! url (-> % .-target .-value))
        search #(do (.preventDefault %)
                    (-> (search %)
                        (.then (fn [body]
                                 (reset! url (:url body))
                                 (reset! items (:items body))))))]
    (fn [lookup _list attrs]
      [:section {:class "list"}
       [:header
        [:form {:action @url
                :method :GET
                :on-submit search}
         [:input {:type "text"
                  :name "url"
                  :default-value @url
                  :on-change set-url
                  :placeholder (:placeholder attrs)}]]]
       [:section {:class "items"}
        (for [item @items]
          (let [list-item (-> item :type lookup)]
            ^{:key (:id item)}
            [:div {:class "list-item"}
             [list-item item]]))]])))
