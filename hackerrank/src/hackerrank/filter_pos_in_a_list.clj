(ns hackerrank.filter-pos-in-a-list
  (:require [clojure.test :refer :all]))

(defn delim-odd-pos 
  ([coll] (delim-odd-pos 1 coll '()))
  ([i coll res]
   (if (empty? coll)
     res
     (if (even? i)
       (recur (inc i) (rest coll) (concat res (list (first coll))))
       (recur (inc i) (rest coll) res)))))

(deftest a-test
  (testing "delim-odd-pos"
    (is (= '(2 4) (delim-odd-pos [1 2 3 4 5])))
    ))

