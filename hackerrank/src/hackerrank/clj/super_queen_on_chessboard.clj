(ns hackerrank.clj.super-queen-on-chessboard-slow
  (:require [clojure.test :refer :all]))


(defn same-row? [pos1 pos2]
  (= (second pos1) (second pos2)))

(defn conflict-diagonally? 
  [[x1 y1] [x2 y2]]
  (let [tmp (double (/ (- y2 y1) (- x2 x1)))]
    (or (= 1.0 tmp) (= -1.0 tmp))))

(defn conflict-in-box?
  [[x1 y1] [x2 y2]]
  (and (>= x2 (- x1 2.0))
       (>= y2 (- y1 2.0))
       (<= y2 (+ y1 2.0))))

(defn conflict? [pos1 pos2]
  (or (same-row? pos1 pos2)
      (conflict-in-box? pos1 pos2)
      (conflict-diagonally? pos1 pos2)
  ))

(defn queens-patterns [N]
  (letfn [(iter [ix res]
            (if (> ix N)
              1
              (reduce +
                      (for [iy (range 1.0 (inc N))
                            :when (not (some #(conflict? [ix iy] %) res))]
                        (iter (inc ix)
                              (cons [ix iy] res))))))]
    (iter 1.0 '())))

(->> (read-line)
     Integer/parseInt
     double
     queens-patterns
     println)

