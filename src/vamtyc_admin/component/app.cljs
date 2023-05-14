(ns vamtyc-admin.component.app
  (:require
   [vamtyc-admin.component.lib.panel :as pnl]
   [vamtyc-admin.component.resources.core :as res]
   [vamtyc-admin.component.resources.request :as req]
   [vamtyc-admin.component.resources.list :as lst]))

(defn app [res]
  [:section {:class "section"}
   [:div {:class "container"}
    [pnl/panel
     [req/render res/lookup (:filter res) {:placeholder "search"}]
     [lst/render res/lookup (:main res)]]]])
