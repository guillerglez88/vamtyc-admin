(ns vamtyc-admin.component.app
  (:require
   [vamtyc-admin.component.resources.core :as res]
   [vamtyc-admin.component.resources.list :as lst]))

(defn app [res]
  [lst/render res/lookup (:main res)])
