(ns vamtyc-admin.server
  (:require
   [ring.middleware.webjars :refer [wrap-webjars]]))

(defn handler [request]
  (if (and (= :get (:request-method request))
           (= "/" (:uri request)))
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (slurp "resource/public/index.html")}
    {:status 404
     :headers {"Content-Type" "text/plain"}
     :body "Not found"}))

(def app (-> handler wrap-webjars))
