(ns cljap.compare
  (:require [clojure.test :refer :all]))

(defn compare-author1 [s1 s2]
  (let [c (compare (:lname s1) (:lname s2))]
    (if (zero? c)
      (compare (:fname s1) (:fname s2))
      c)))

(defn compare-author2 [s1 s2]
  (let [project-author (juxt :lname :fname)]
    (compare (project-author s1) (project-author s2))))

(deftest test-compare
  (let [s1 {:fname "Jeff" :lname "Smith"}
        s2 {:fname "Bill" :lname "Smith"}]
    (testing "test compare"
      (is (= #{s2 s1} (sorted-set-by compare-author1 s1 s2)))
      (is (= (sorted-set-by compare-author1 s1 s2)
             (sorted-set-by compare-author2 s1 s2)))
      ))
  )

