(ns vamtyc-admin.component.resources.list
  (:require
   [lambdaisland.fetch :as fetch]
   [reagent.core :as r]))

(defn search [url]
  (-> (str "http://localhost:3000" url)
      (fetch/request {:method "GET"
                      :headers {"Accept" "application/json"}})
      (.then #(-> % :body (js->clj :keywordize-keys true)))))

(defn pagination [search nav]
  [:nav
   [:a {:title "first"
        :href (:first nav)
        :on-click #(search % (:first nav))}
    [:span {:class "icon"}
     [:i {:class "fa-solid fa-circle"}]]]
   [:a {:title "prev"
        :href (:prev nav)
        :on-click #(search % (:prev nav))}
    [:span {:class "icon"}
     [:i {:class "fa-solid fa-caret-left"}]]]
   [:a {:title "next"
        :href (:next nav)
        :on-click #(search % (:next nav))}
    [:span {:class "icon"}
     [:i {:class "fa-solid fa-caret-right"}]]]
   [:a {:title "last"
        :href (:last nav)
        :on-click #(search % (:last nav))}
    [:span {:class "icon"}
     [:i {:class "fa-solid fa-circle"}]]]])

(defn render [_lookup list _attrs]
  (let [state (r/atom list)
        set-url #(reset! state (assoc @state :url (-> % .-target .-value)))
        form-search #(do (.preventDefault %)
                         (-> (:url @state)
                             (search)
                             (.then (fn [body] (reset! state body)))))
        nav-search #(do (.preventDefault %1)
                        (-> (search %2)
                            (.then (fn [body] (reset! state body)))))]
    (fn [lookup _list attrs]
      [:section {:class "list"}
       [:header
        [:form {:action (:url @state)
                :method :GET
                :on-submit form-search}
         [:input {:type "text"
                  :name "url"
                  :value (:url @state)
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
        [pagination nav-search (:nav @state)]]])))
