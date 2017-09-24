(ns prog-challenge.p5
  (:require [clojure.test :refer :all]))

;; Problem 5
;; Write a program that outputs all possibilities to put + or - or nothing between the 
;; numbers 1, 2, ..., 9 (in this order) such that the result is always 100. 
;; For example: 1 + 2 + 34 – 5 + 67 – 8 + 9 = 100.

     
(defn do-op [op n1 n2]
  (cond (= op '+) (+ n1 n2)
        (= op '-) (- n1 n2)
        (= op 'n) (+ (* n1 10) n2)))

(defn apply-n [coll]
  (if (< (count coll) 3)
    coll
    (let [n1 (first coll)
          op (second coll)
          n2 (nth coll 2)]
      (if (= op 'n)
        (recur (cons (do-op op n1 n2) (rest (rest (rest coll)))))
        (cons n1 (cons op (apply-n (rest (rest coll)))))))))

(defn calc-op [coll]
  (if (< (count coll) 3)
    (first coll)
    (let [n1 (first coll)
          op (second coll)
          n2 (nth coll 2)]
        (recur (cons (do-op op n1 n2) (rest (rest (rest coll))))))))

(defn op-num-array
  ([coll] (op-num-array coll 1 '()))
  ([coll n res]
   (if (empty? coll)
     (concat res (list n))
     (recur (rest coll) 
            (+ n 1)
            (concat res (list n (first coll)))))))

(defn make-all-posibilities
  "make all possibilities to put + or - or nothing between the 
  numbers 1, 2, ..., 9 (in this order)."
  [n res]
  (if (zero? n)
    (apply-n (op-num-array res))
    (let [op (if (= n 1) list concat)]
      (op (make-all-posibilities (dec n) (cons '+ res))
          (make-all-posibilities (dec n) (cons '- res))
          (make-all-posibilities (dec n) (cons 'n res))))))

(defn output-result-always-100 []
  (filter #(= 100 (calc-op %)) (make-all-posibilities 8 '())))

(deftest p5-test
  (testing "test output-result-always-100"
    (is (= '((1 + 23 - 4 + 56 + 7 + 8 + 9)
             (12 + 3 - 4 + 5 + 67 + 8 + 9)
             (1 + 2 + 34 - 5 + 67 - 8 + 9)
             (1 + 2 + 3 - 4 + 5 + 6 + 78 + 9)
             (123 - 4 - 5 - 6 - 7 + 8 - 9)
             (123 + 45 - 67 + 8 - 9)
             (1 + 23 - 4 + 5 + 6 + 78 - 9)
             (12 - 3 - 4 + 5 - 6 + 7 + 89)
             (12 + 3 + 4 + 5 - 6 - 7 + 89)
             (123 - 45 - 67 + 89)
             (123 + 4 - 5 + 67 - 89))
           (output-result-always-100)))))

