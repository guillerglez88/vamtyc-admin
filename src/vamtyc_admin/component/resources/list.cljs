(ns vamtyc-admin.component.resources.list
  (:require
   [reagent.core :as r]
   [vamtyc-admin.lib.store :as store]
   [vamtyc-admin.lib.util :as util]))

(defn set-url
  ([resource inline?]
   (partial set-url resource inline?))
  ([resource inline? evt]
   (let [url (-> evt .-target .-value)]
     (reset! resource (assoc @resource :url url))
     (reset! inline? (= url (util/inline-text url))))))

(defn search
  ([resource inline?]
   (partial search resource inline?))
  ([resource inline? url]
   (-> (util/inline-text url)
       (store/search)
       (.then (fn [body]
                (let [transformed (if @inline? (:url body) (-> body :url util/multiline-url))]
                  (->> (assoc body :url transformed)
                       (reset! resource))))))))

(defn form-submit
  ([resource inline?]
   (partial form-submit resource inline?))
  ([resource inline? evt]
   (.preventDefault evt)
   (search resource inline? (:url @resource))))

(defn input-keydown
  ([resource inline?]
   (partial input-keydown resource inline?))
  ([resource inline? evt]
   (when (util/hot-key? evt :ctrlKey true :key "Enter")
     (search resource inline? (:url @resource)))))

(defn navigate
  ([resource inline?]
   (partial navigate resource inline?))
  ([resource inline? evt url]
   (.preventDefault evt)
   (search resource inline? url)))

(defn select-item
  ([item-url selected]
   (partial select-item item-url selected))
  ([item-url selected evt]
   (.preventDefault evt)
   (reset! selected item-url)))

(defn pagination [search curr nav]
  (let [inline-curr (util/inline-text curr)
        first (:first nav)
        prev (:prev nav)
        next (:next nav)
        last (:last nav)
        first? (= first inline-curr)
        last? (= last inline-curr)]
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
  (let [resource (r/atom list)
        inline? (r/atom true)
        selected (r/atom nil)]
    (search resource inline? (:url @resource))
    (fn [lookup _list attrs]
      (let [selected-url @selected]
        [:section {:class "list"}
         [:header
          [:form {:action (:url @resource)
                  :method :GET
                  :on-submit (form-submit resource inline?)}
           [:textarea {:type "text"
                       :name "url"
                       :value (:url @resource)
                       :on-change (set-url resource inline?)
                       :on-key-down (input-keydown resource inline?)
                       :rows "5"
                       :placeholder (:placeholder attrs)}]]
          [:p
           [:span {:class "keyword kw-2"} "CTRL"]
           [:span {:class "keyword kw-2"} "Enter"]]]
         [:section {:class "items"}
          (for [item (:items @resource)]
            (let [list-item (-> item :type lookup)
                  item-url (:url item)]
              ^{:key (:id item)}
              [:a {:class (str "list-item"
                               (when (= selected-url item-url) " is-selected"))
                   :href item-url
                   :on-click (select-item item-url selected)}
               [list-item item {:mode :list-item}]]))]
         [:footer
          [:p (str "total: " (:total @resource))]
          [pagination (navigate resource inline?) (:url @resource) (:nav @resource)]]]))))

(def mode-displays
  {:default default
   :list-item list-item})

(defn render [lookup list attrs]
  (let [mode (-> attrs :mode (or :default))
        display (get mode-displays mode)]
    [display lookup list attrs]))
