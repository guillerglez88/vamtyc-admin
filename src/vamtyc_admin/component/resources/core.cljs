(ns vamtyc-admin.component.resources.core
  (:require
   [vamtyc-admin.component.resources.resource :as res]
   [vamtyc-admin.component.resources.list :as lst]
   [vamtyc-admin.component.resources.request :as req]))

(def wkr-ddl      "/Coding/wellknown-resources?code=ddl")
(def wkr-resource "/Coding/wellknown-resources?code=resource")
(def wkr-route    "/Coding/wellknown-resources?code=route")
(def wkr-queryp   "/Coding/wellknown-resources?code=queryp")
(def wkr-list     "/Coding/wellknown-resources?code=list")
(def wkr-seq      "/Coding/wellknown-resources?code=seq")
(def wkr-etag     "/Coding/wellknown-resources?code=seq-etag")
(def wkr-request  "/Coding/wellknown-resources?code=request")

(defn not-supported [_lookup _res]
  [:div
    :p "not supported"])

(def component-map {wkr-ddl       not-supported
                    wkr-resource  res/render
                    wkr-route     not-supported
                    wkr-queryp    not-supported
                    wkr-list      lst/render
                    wkr-seq       not-supported
                    wkr-etag      not-supported
                    wkr-request   req/render})

(defn lookup [code]
  (-> (get component-map code)
      (partial lookup)))
