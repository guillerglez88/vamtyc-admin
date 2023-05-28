(ns vamtyc-admin.component.editor
  (:require
   [vamtyc-admin.component.resources.lib.table :as table]))

(defn render [res]
  (table/render res))
