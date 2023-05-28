(ns vamtyc-admin.component.app
  (:require
   [vamtyc-admin.component.resources.core :as res]
   [vamtyc-admin.component.resources.list :as lst]
   [reagent.core :as r]
   [vamtyc-admin.lib.store :as store]
   [vamtyc-admin.component.editor :as editor]))

(def bx-list-search "/Coding/vamtyc-admin-behavour?code=list-search")

(defn resolve-bxs [res target]
  (->> (:behaviour res)
       (filter #(-> % :target (= target)))
       (vec)))

(defn codes-set [bxs]
  (->> (identity bxs)
       (map :code)
       (into #{})))

(defn list-attrs [bxs]
  (let [codes (codes-set bxs)
        flag? #(-> % codes nil? not)]
    {:search (flag? bx-list-search)}))

(defn edit [url editor-res]
  (-> (store/read url)
      (.then #(reset! editor-res %))))

(defn app [res]
  (let [editor-res (r/atom nil)
        bxs (resolve-bxs res ["main"])
        main-attrs (list-attrs bxs)
        attrs (merge main-attrs {:on-selected #(edit % editor-res)})]
    (fn [_res]
      [:div {:class "workspace"}
       [:section {:class "explorer"}
        [lst/render res/lookup (:main res) attrs]]
       [:section {:class "editor"}
        [:section
         [:header
          [:span {:class "keyword"}
           (:url @editor-res)]
          [:nav
           [:a {:class "keyword kw-3"} "JSON"]
           [:a {:class "keyword kw-3"} "YAML"]
           [:a {:class "keyword kw-3"} "TABLE"]]]
         [:section
          (when-let [eres @editor-res]
            [editor/render eres])]
         [:footer]]]])))
