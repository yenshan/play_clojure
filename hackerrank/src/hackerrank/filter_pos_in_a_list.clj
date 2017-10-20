(ns hackerrank.filter-pos-in-a-list
  (:require [clojure.test :refer :all]))


(defn delim-odd-pos [coll]
  (letfn [(iter [i coll res]
            (if (empty? coll)
              res
              (if (even? i)
                (recur (inc i) (rest coll) (conj res (first coll)))
                (recur (inc i) (rest coll) res))))]
    (iter 1 coll [])))

(defn delim-odd-pos2 [coll]
  (->> coll
       (map (fn [a b] [a b]) (range 1 (inc (count coll))))
       (filter (fn [[a b]] (even? a)))
       (map (fn [[a b]] b))))


(deftest a-test
  (testing "delim-odd-pos"
    (is (= [2 4] (delim-odd-pos [1 2 3 4 5])))
    (is (= [2 4] (delim-odd-pos2 [1 2 3 4 5])))
    ))

