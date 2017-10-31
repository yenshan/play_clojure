;;
;; https://www.hackerrank.com/challenges/super-queens-on-a-chessboard/problem
;;
(ns hackerrank.clj.super-queen-on-chessboard
  (:require [clojure.test :refer :all]))

;;
;; this solution takes 8.7 seconds when N = 14
;;

(defn seqp [^long x ^long y ^long k]
  (+ x (* y k)))

(defn make-conflict-function
  [[^long x ^long y] ^long k]
  (let [ng-positions (reduce #(assoc %1 %2 1)
                             { (seqp -1 -2 k) 1, (seqp -2 -1 k) 1,
                              (seqp -1 2 k) 1, (seqp -2 1 k) 1}
                             (apply concat 
                                    (for [i (range 1 k)]
                                      (list (seqp (- x i) y k)
                                            (seqp (- x i) (- y i) k)
                                            (seqp (- x i) (+ y i) k)
                                            ))))]
    (fn [[^long x1 ^long y1] [^long x2 ^long y2]]
      (contains? ng-positions (seqp (- x2 x1) (- y2 y1) k)))))

(defn queens-patterns [N]
  (let [conflict? (make-conflict-function [0 0] N)]
    (letfn [(iter [^long ix res]
              (if (> ix N)
                1
                (apply +
                        (for [iy (range 1 (inc N))
                              :when (not-any? #(conflict? [ix iy] %) res)]
                          (iter (inc ix)
                                (cons [ix iy] res))))))]
      (iter 1 '()))))

(->> (read-line)
     Integer/parseInt
     queens-patterns
     println)

