(ns vamtyc-admin.component.resources.list
  (:require
   [reagent.core :as r]
   [vamtyc-admin.lib.store :as store]
   [vamtyc-admin.lib.util :as util]))

(defn pagination [search curr nav]
  (let [first (:first nav)
        prev (:prev nav)
        next (:next nav)
        last (:last nav)
        first? (= first curr)
        last? (= last curr)]
    [:nav
     [:a {:class (when first? "is-active")
          :title "first"
          :href first
          :on-click #(search % first)}
      [:span {:class "icon"}
       [:i {:class "fa-solid fa-circle"}]]]
     [:a {:title "prev"
          :href prev
          :on-click #(search % prev)}
      [:span {:class "icon"}
       [:i {:class "fa-solid fa-caret-left"}]]]
     [:a {:title "next"
          :href next
          :on-click #(search % next)}
      [:span {:class "icon"}
       [:i {:class "fa-solid fa-caret-right"}]]]
     [:a {:class (when last? "is-active")
          :title "last"
          :href last
          :on-click #(search % last)}
      [:span {:class "icon"}
       [:i {:class "fa-solid fa-circle"}]]]]))

(defn list-item [_lookup list _attrs]
  [:div
   [:span {:class "icon"}
    [:i {:class "fa-solid fa-list"}]]
   (:url list)])

(defn default [_lookup list _attrs]
  (let [state (r/atom list)
        set-url #(reset! state (assoc @state :url (-> % .-target .-value)))
        action-search #(-> (util/inline-text %)
                           (store/search)
                           (.then (fn [body]
                                    (->> (assoc body :url (:url @state))
                                         (reset! state)))))
        form-search #(do (.preventDefault %)
                         (action-search (:url @state)))
        input-key #(when (util/hot-key? % :ctrlKey true :key "Enter")
                     (action-search (:url @state)))
        nav-search #(do (.preventDefault %1)
                        (action-search %2))]
    (action-search (:url @state))
    (fn [lookup _list attrs]
      [:section {:class "list"}
       [:header
        [:form {:action (:url @state)
                :method :GET
                :on-submit form-search}
         [:textarea {:type "text"
                     :name "url"
                     :value (:url @state)
                     :on-change set-url
                     :on-key-down input-key
                     :rows "5"
                     :placeholder (:placeholder attrs)}]]
        [:p
         [:span {:class "keyword kw-2"} "CTRL"]
         [:span {:class "keyword kw-2"} "Enter"]]]
       [:section {:class "items"}
        (for [item (:items @state)]
          (let [list-item (-> item :type lookup)]
            ^{:key (:id item)}
            [:div {:class "list-item"}
             [list-item item {:mode :list-item}]]))]
       [:footer
        [:p (str "total: " (:total @state))]
        [pagination nav-search (:url @state) (:nav @state)]]])))

(def mode-displays
  {:default default
   :list-item list-item})

(defn render [lookup list attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup list attrs]))
