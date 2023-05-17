(ns vamtyc-admin.component.app-test
  (:require [vamtyc-admin.component.app :as sut]
            [cljs.test :as t :include-macros true :refer [deftest testing is]]))

(deftest resolve-bxs-test
  (testing "Can resolve behaviours by target"
    (let [res {:behaviour [{:code "/Coding/vamtyc-admin-behavour?code=list-search"
                            :desc "Allow main list search"
                            :target ["main"]}]}]
      (is (= (:behaviour res)
             (sut/resolve-bxs res ["main"]))))))

(deftest codes-set-test
  (testing "Can extract behavours codes as hash-set"
    (is (= #{"/Coding/vamtyc-admin-behavour?code=list-search"}
           (sut/codes-set [{:code "/Coding/vamtyc-admin-behavour?code=list-search"
                            :desc "Allow main list search"
                            :target ["main"]}])))))

(deftest list-attrs-test
  (testing "Can compute list attrs from behaviours"
    (is (= {:search true}
           (sut/list-attrs [{:code "/Coding/vamtyc-admin-behavour?code=list-search"
                             :desc "Allow main list search"
                             :target ["main"]}])))))
