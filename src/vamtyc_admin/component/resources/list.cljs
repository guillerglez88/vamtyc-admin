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

(defn pagination [nav]
  [:nav
   [:a {:title "first"
        :href (:first nav)}
    [:span {:class "icon"}
     [:i {:class "fa-solid fa-circle"}]]]
   [:a {:title "prev"
        :href (:prev nav)}
    [:span {:class "icon"}
     [:i {:class "fa-solid fa-caret-left"}]]]
   [:a {:title "next"
        :href (:next nav)}
    [:span {:class "icon"}
     [:i {:class "fa-solid fa-caret-right"}]]]
   [:a {:title "last"
        :href (:last nav)}
    [:span {:class "icon"}
     [:i {:class "fa-solid fa-circle"}]]]])

(defn render [_lookup list _attrs]
  (let [state (r/atom list)
        set-url #(reset! state (assoc @state :url (-> % .-target .-value)))
        search #(do (.preventDefault %)
                    (-> (search %)
                        (.then (fn [body] (reset! state body)))))]
    (fn [lookup _list attrs]
      [:section {:class "list"}
       [:header
        [:form {:action (:url @state)
                :method :GET
                :on-submit search}
         [:input {:type "text"
                  :name "url"
                  :default-value (:url @state)
                  :on-change set-url
                  :placeholder (:placeholder attrs)}]]]
       [:section {:class "items"}
        (for [item (:items @state)]
          (let [list-item (-> item :type lookup)]
            ^{:key (:id item)}
            [:div {:class "list-item"}
             [list-item item]]))]
       [:footer
        [:p (str "total: " (:total @state))]
        [pagination (:nav @state)]]])))
