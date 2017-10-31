(ns hackerrank.clj.super-queen-on-chessboard
  (:require [clojure.test :refer :all]))

(defn make-conflict-function
  [[x y] k]
  (let [ng-positions (reduce #(assoc %1 %2 1)
                             {[-1 -2] 1,  [-2 -1] 1, [-1, 2] 1, [-2, 1] 1}
                             (apply concat 
                                    (for [i (range 1 k)]
                                      (list [(- x i) y]
                                            [(- x i) (- y i)]
                                            [(- x i) (+ y i)]
                                            ))))]
    (fn [[x1 y1] [x2 y2]]
      (not (nil? (get ng-positions [(- x2 x1) (- y2 y1)]))))))
      

(defn queens-patterns [N]
  (let [conflict? (make-conflict-function [0 0] N)]
    (letfn [(iter [ix res]
              (if (> ix N)
                1
                (reduce +
                        (for [iy (range 1 (inc N))
                              :when (not-any? #(conflict? [ix iy] %) res)]
                          (iter (inc ix)
                                (cons [ix iy] res))))))]
      (iter 1 '()))))

(->> (read-line)
     Integer/parseInt
     queens-patterns
     println)

