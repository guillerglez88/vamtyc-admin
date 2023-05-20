(ns vamtyc-admin.component.codings.handler
  (:require
    [vamtyc-admin.lib.store :as store]))

(defn render [code]
  (-> (store/search code)))
