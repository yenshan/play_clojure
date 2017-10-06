(ns hackerrank.area-polygon
  (:require [clojure.test :refer :all]))

;;
;; ref:https://www.wikihow.com/Calculate-the-Area-of-a-Polygo://www.wikihow.com/Calculate-the-Area-of-a-Polygon:w
;;

(defn sum-products
  ([f coll] (sum-products f (conj coll (first coll)) 0))
  ([f coll res]
   (if (< (count coll) 2)
     res
     (recur f (rest coll) (+ res (f (first coll) (second coll)))))))

(defn area-polygon [coll]
  (let [sum-l (sum-products #(* (first %1) (second %2)) coll)
        sum-r (sum-products #(* (first %2) (second %1)) coll)]
    (/ (Math/abs (- sum-l sum-r)) 2.0)))

(deftest a-test
  (testing "area-polygon"
    (is (= 60.0 (area-polygon [[-3 -2] [-1 4] [6 1] [3 10] [-4 9]])))
    ))
