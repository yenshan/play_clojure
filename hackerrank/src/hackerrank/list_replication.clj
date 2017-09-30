(ns hackerrank.list-replication
  (:require [clojure.test :refer :all]))

(defn list-replicate
  ([num lst] (list-replicate num lst '()))
  ([num lst res]
   (if (empty? lst)
     res
     (recur num 
            (rest lst)
            (concat res 
                    (take num (repeat (first lst))))))))

(deftest test-this
  (testing "test list-replicate"
    (is (= '(1 1 1 2 2 2 3 3 3) (list-replicate 3 [1 2 3])))
    ))
