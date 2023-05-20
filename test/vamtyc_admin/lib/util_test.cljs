(ns vamtyc-admin.lib.util-test
  (:require
   [cljs.test :as t :include-macros true :refer [deftest is testing]]
   [vamtyc-admin.lib.util :as sut]))

(deftest padding-test
  (testing "Can add padding to text"
    (is (= "test------"
           (sut/padding "test" 10 "-")))))
