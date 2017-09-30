(ns codechef.rainbow-array
  (:require [clojure.test :refer :all]))


; your code goes here
;; https://www.codechef.com/problems/RAINBOWA
;;

(defn split-by-center
  ([coll] (split-by-center coll '()))
  ([coll res]
   (let [e1 (first coll)
         e2 (second coll)]
     (cond (or (= e1 e2) (= e2 (inc e1)))
             (recur (rest coll)
                    (concat res (list e1)))
           (> e1 e2)
             [res (rest coll)]
           :else
             ['() coll]))))

(defn rainbow-array? [coll]
  (let [tmp (split-by-center coll)]
    (= (first tmp)
       (reverse (second tmp)))))

(deftest raibow-array-test
  (testing "test rainbow-array?"
    (is (rainbow-array? [1 2 3 4 4 5 6 6 6 7 6 6 6 5 4 4 3 2 1]))
    (is (not (rainbow-array? [1 2 3 4 5 6 7 6 5 4 3 2 1 1])))
    (is (not (rainbow-array? [1 2 3 4 5 6 8 6 5 4 3 2 1])))
    ))
