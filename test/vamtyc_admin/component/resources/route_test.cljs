(ns vamtyc-admin.component.resources.route-test
  (:require [vamtyc-admin.component.resources.route :as sut]
            [cljs.test :as t :include-macros true :refer [deftest testing is]]))

(deftest str-path-test
  (testing "Can stringify route path"
    (is (= "/Ddl/:_id"
           (sut/str-path [{:code "/Coding/wellknown-params?code=type", :name "_type", :value "Ddl"}
                          {:code "/Coding/wellknown-params?code=id", :name "_id"}])))
    (is (= "/Resource"
           (sut/str-path [{:code "/Coding/wellknown-params?code=type", :name "_type", :value "Resource"}])))
    (is (= "/"
           (sut/str-path nil)))))

(deftest str-method-test
  (testing "Can stringify route method"
    (is (= "GET"
           (sut/str-method :GET)))
    (is (= "GET"
           (sut/str-method :get)))
    (is (= "GET"
           (sut/str-method "get")))
    (is (= "*"
           (sut/str-method :*)))))

(deftest padding-test
  (testing "Can add padding to text"
    (is (= "test------"
           (sut/padding "test" 10 "-")))))
