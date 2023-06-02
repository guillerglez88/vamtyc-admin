(ns vamtyc-admin.lib.util-test
  (:require
   [cljs.test :as t :include-macros true :refer [deftest is testing]]
   [vamtyc-admin.lib.util :as sut]))

(deftest meta-prop?-test
  (testing "Can check if key is a meta-prop"
    (is (= false
           (sut/meta-prop? :path)))
    (is (= false
           (sut/meta-prop? "name")))
    (is (= true
           (sut/meta-prop? "type")))
    (doseq [key sut/meta-props]
      (is (= true
             (sut/meta-prop? key))))))

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

(deftest mapseg-test
  (testing "Can segment sequence producing a tuple [head item tail] for each item in the sequence"
    (is (= [[[] 1 [2 3 4 5]]
            [[1] 2 [3 4 5]]
            [[1 2] 3 [4 5]]
            [[1 2 3] 4 [5]]
            [[1 2 3 4] 5 []]]
           (sut/mapseg [1 2 3 4 5] identity)))
    (is (= [[[] 1 [2 3 4 5 6 7]]
            [[1] 2 [3 4 5 6 7]]
            [[1 2] 3 [4 5 6 7]]
            [[1 2 3] 4 [5 6 7]]
            [[1 2 3 4] 5 [6 7]]
            [[1 2 3 4 5] 6 [7]]
            [[1 2 3 4 5 6] 7 []]]
           (sut/mapseg [1 2 3 4 5 6 7] identity)))
    (is (= [[[] nil []]]
           (sut/mapseg [] identity)))
    (is (= [[[] 1 []]]
           (sut/mapseg [1] identity)))
    (is (= [[[] nil []]]
           (sut/mapseg nil identity)))
    (is (= [[[] 1 [2 3 4 5]]
            [[2] 2 [3 4 5]]
            [[2 4] 3 [4 5]]
            [[2 4 6] 4 [5]]
            [[2 4 6 8] 5 []]]
           (sut/mapseg [1 2 3 4 5] (partial * 2))))))

(deftest flat-obj-test
  (testing "Can convert anything into a seq of pairs [key val]"
    (is (= [["" 1]]
           (sut/flat-obj 1)))
    (is (= [["" nil]]
           (sut/flat-obj nil)))
    (is (= [["a" nil]]
           (sut/flat-obj {:a nil})))
    (is (= [["a" 1]
            ["b" 2]]
           (sut/flat-obj {:a 1, :b 2})))
    (is (= [["name.given" "John"]
            ["name.given" "M."]
            ["name.family" "Doe"]
            ["gender" :male]]
           (sut/flat-obj {:name [{:given ["John" "M."]
                                  :family "Doe"}]
                          :gender :male})))
    (is (= [["path.name" "of"]
            ["desc" "Filter Resource by of"]
            ["type" "Queryp"]]
           (sut/flat-obj {:path [{:name "of"}],
                          :desc "Filter Resource by of",
                          :type "Queryp",})))
    (is (= [["path.name" "path"]
            ["path.filter.code" "/Coding/wellknown-params?code=type"]
            ["path.collection" true]
            ["path.name" "value"]
            ["type" "Queryp"]]
           (sut/flat-obj {:path [{:name "path",
                                  :filter {:code "/Coding/wellknown-params?code=type"},
                                  :collection true}
                                 {:name "value"}],
                          :type "Queryp",})))))
