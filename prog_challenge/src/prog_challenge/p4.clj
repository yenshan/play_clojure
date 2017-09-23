(ns prog-challenge.p4
  (:require [clojure.test :refer :all]))

;; Problem 4
;; Write a function that given a list of non negative integers, arranges them such
;; that they form the largest possible number. For example, given [50, 2, 1, 9], 
;; the largest formed number is 95021.

(defn num->coll
  ([n] (num->coll n []))
  ([n res] 
   (if (zero? n)
     res
     (recur (unchecked-divide-int n 10) (cons (mod n 10) res)))))

(defn comp-num [n1 n2]
  (letfn [(iter [col1 col2]
            (if (empty? col1)
              0
              (let [msd1 (first col1)
                    msd2 (first col2)]
                (cond (> msd1 msd2) 1
                      (< msd1 msd2) -1
                      :else (recur (rest col1) (rest col2))))))]
    (iter (num->coll n1) (num->coll n2))))

(defn largest-formed-number [coll]
  (apply str (sort #(comp-num %2 %1) coll)))

(deftest p4-test
  (testing "test comp-num"
    (is (= -1 (comp-num 200 21)))
    )
  (testing "largest-formed-number"
    (is (= "95021" (largest-formed-number [50 2 1 9])))
    (is (= "9862415" (largest-formed-number [24 15 9 6 8])))
    (is (= "908621200" (largest-formed-number [200 21 90 6 8])))
    ))
