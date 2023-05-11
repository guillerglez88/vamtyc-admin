(ns vamtyc-admin.core-test
    (:require
     [cljs.test :refer-macros [deftest is testing]]
     [vamtyc-admin.core :as sut]))

(deftest can-test
  (testing "Can test"
    (is (= 2 (* 1 2)))))
