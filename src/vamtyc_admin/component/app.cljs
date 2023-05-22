(ns vamtyc-admin.component.app
  (:require
   [vamtyc-admin.component.resources.core :as res]
   [vamtyc-admin.component.resources.list :as lst]))

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

(defn app [res]
  (let [bxs (resolve-bxs res ["main"])
        main-attrs (list-attrs bxs)]
    [:div {:class "workspace"}
     [:section {:class "explorer"}
      [lst/render res/lookup (:main res) main-attrs]]
     [:section {:class "editor"}]]))
