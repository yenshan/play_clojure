(ns hackerrank.functional-programming.introduction.list-replication
  (:require [clojure.test :refer :all]))

(defn list-replicate [num lst]
  (letfn [(iter [lst res]
            (if (empty? lst)
              res
              (recur (rest lst)
                     (concat res (take num (repeat (first lst)))))))]
    (iter lst [])))


(defn list-replicate2 [n coll]
  (->> coll
       (map #(take n (repeat %)))
       (apply concat)))

(defn list-replicate3 [n coll]
  (->> (repeat coll)
       (take n)
       (apply interleave)))

(deftest test-this
  (testing "test list-replicate"
    (is (= '(1 1 1 2 2 2 3 3 3) (list-replicate 3 [1 2 3])))
    (is (= '(1 1 1 2 2 2 3 3 3) (list-replicate2 3 [1 2 3])))
    (is (= '(1 1 1 2 2 2 3 3 3) (list-replicate3 3 [1 2 3])))
    ))
