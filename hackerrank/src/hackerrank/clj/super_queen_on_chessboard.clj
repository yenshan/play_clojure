;;
;; https://www.hackerrank.com/challenges/super-queens-on-a-chessboard/problem
;;
(ns hackerrank.clj.super-queen-on-chessboard
  (:require [clojure.test :refer :all]))

;;
;; this solution takes under 7 seconds when N = 14
;;
(defn index [^long x ^long y ^long k]
  (+ x (* y k)))

(defn make-conflict-function
  [[^long x ^long y] ^long k]
  (let [ng-positions (reduce (fn [res [x y]] (assoc res (index x y k) 1))
                             {}
                             (loop [i 1, res [[-1 -2] [-2 -1] [-1 2] [-2 1]]]
                               (if (> i k)
                                 res
                                 (recur (inc i)
                                        (conj res 
                                              [(- x i) y] 
                                              [(- x i) (- y i)]
                                              [(- x i) (+ y i)])))))]
    (fn [p1 p2]
      (contains? ng-positions (- p2 p1)))))

(defn queens-patterns [N]
  (let [conflict? (make-conflict-function [0 0] N)]
    (letfn [(iter [^long ix res]
              (if (> ix N)
                1
                (apply +
                        (for [iy (range 1 (inc N))
                              :let [p (index ix iy N)]
                              :when (not-any? #(conflict? p %) res)]
                          (iter (inc ix)
                                (cons p res))))))]
      (iter 1 '()))))

(->> (read-line)
     Integer/parseInt
     queens-patterns
     println)

