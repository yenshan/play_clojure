(ns cljap.compare-test
  (:require [clojure.test :refer :all]
            [cljap.compare :refer :all]))


(deftest test-compare
  (let [s1 {:fname "Jeff" :lname "Smith"}
        s2 {:fname "Bill" :lname "Smith"}]
    (testing "test compare"
      (is (= #{s2 s1} (sorted-set-by compare-author1 s1 s2)))
      (is (= (sorted-set-by compare-author1 s1 s2)
             (sorted-set-by compare-author2 s1 s2)))
      ))
  )

