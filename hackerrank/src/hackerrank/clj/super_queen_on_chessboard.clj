;;
;; https://www.hackerrank.com/challenges/super-queens-on-a-chessboard/problem
;;
(ns hackerrank.clj.super-queen-on-chessboard
  (:require [clojure.test :refer :all]))

;;
;; this solution takes 8.7 seconds when N = 14
;;

(defn index [^long x ^long y ^long k]
  (+ x (* y k)))

(defn make-conflict-function
  [[^long x ^long y] ^long k]
  (let [ng-positions (reduce (fn [res [x y]] (assoc res (index x y k) 1))
                             { (index -1 -2 k) 1, (index -2 -1 k) 1,
                              (index -1 2 k) 1, (index -2 1 k) 1}
                             (loop [i 1, res []]
                               (if (> i k)
                                 res
                                 (recur (inc i)
                                        (conj res 
                                              [(- x i) y] 
                                              [(- x i) (- y i)]
                                              [(- x i) (+ y i)])))))]
    (fn [[^long x1 ^long y1] [^long x2 ^long y2]]
      (contains? ng-positions (index (- x2 x1) (- y2 y1) k)))))

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

