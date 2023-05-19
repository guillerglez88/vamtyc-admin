(ns vamtyc-admin.component.resources.route-test
  (:require [vamtyc-admin.component.resources.route :as sut]
            [cljs.test :as t :include-macros true :refer [deftest testing is]]))

(deftest str-route-test
  (testing "Can stringify route path"
    (let [get-route {:path [{:code "/Coding/wellknown-params?code=type", :name "_type", :value "Ddl"}
                            {:code "/Coding/wellknown-params?code=id", :name "_id"}]
                     :method "GET"
                     :name "read-ddl"
                     :type "Route"
                     :etag "15"
                     :created "2023-05-06 16:29:39.01568"
                     :modified "2023-05-06 16:29:39.01568"
                     :id "ac72cf9e-779b-43cb-b811-568f95e6ea31"
                     :resource "/Resource/ddl"
                     :url "/Route/ac72cf9e-779b-43cb-b811-568f95e6ea31"
                     :code "/Coding/handlers?code=read"}]
      (is (= "GET    /Ddl/:_id"
             (sut/str-route get-route))))))
