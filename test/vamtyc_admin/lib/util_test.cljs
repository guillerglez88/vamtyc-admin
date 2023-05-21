(ns vamtyc-admin.lib.util-test
  (:require
   [cljs.test :as t :include-macros true :refer [deftest is testing]]
   [vamtyc-admin.lib.util :as sut]))

(deftest padding-test
  (testing "Can add padding to text"
    (is (= "test------"
           (sut/padding "test" 10 "-")))))

(deftest multiline-url-test
  (testing "Can convert url to multiline"
    (is (= "/List\n  ?_of=Resource\n  &_limit=10\n  &_offset=0"
           (sut/multiline-url "/List?_of=Resource&_limit=10&_offset=0")))))

(deftest inline-text-test
  (testing "Can convert text from multiline to inline"
    (is (= "/List?_of=Resource&_limit=10&_offset=0"
           (sut/inline-text "/List\n  ?_of=Resource\n  &_limit=10\n  &_offset=0")))))

(deftest hot-key?-test
  (testing "Can check for evt hot-key"
    (is (= true
           (sut/hot-key? (clj->js {:target {}
                                   :ctrlKey true
                                   :key "Enter"})
                         :ctrlKey true :key "Enter")))
    (is (= false
           (sut/hot-key? (clj->js {:target {}
                                   :ctrlKey true
                                   :key "Control"})
                         :ctrlKey true :key "Enter")))))
