(ns hackerrank.permiter-polygon
  (:require [clojure.test :refer :all]))

(defn square [x] (* x x))

(defn len [p1 p2]
  (let [x1 (first p1)
        y1 (second p1)
        x2 (first p2)
        y2 (second p2)]
    (Math/sqrt (+ (square (- x2 x1)) 
                  (square (- y2 y1))))))

(defn perimeter
  ([coll] (perimeter (conj coll (first coll)) 0))
  ([coll res]
   (if (< (count coll) 2)
     res
     (recur (rest coll)
            (+ res (len (first coll) (second coll)))))))

(deftest a-test
  (testing "perimeter"
    (is (= 4.0 (perimeter [[0 0] [0 1] [1 1] [1 0]])))
    ))
