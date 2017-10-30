(ns hackerrank.clj.super-queen-on-chessboard3
  (:require [clojure.test :refer :all]))

(defn same-row? [pos1 pos2]
  (= (second pos1) (second pos2)))

(defn conflict-diagonally? 
  [[x1 y1] [x2 y2]]
  (if (> y1 y2)
    (= (- y1 y2) (- x1 x2))
    (= (- y2 y1) (- x1 x2))))

(defn conflict-in-box?
  [[x1 y1] [x2 y2]]
    (and (>= x2 (- x1 2)) 
         (>= y2 (- y1 2)) 
         (<= y2 (+ y1 2))))

(defn conflict? [pos1 pos2]
  (or (same-row? pos1 pos2)
      (conflict-in-box? pos1 pos2)
      (conflict-diagonally? pos1 pos2)
  ))

;;
;; this solutoin takes 18 seconds when N = 14
;;
(defn queens [N]
  (letfn [(iter [ix res]
            (if (> ix N)
              1
              (apply +
                     (for [iy (range 1 (inc N))
                           :when (not-any? #(conflict? [ix iy] %) res)]
                       (iter (inc ix)
                             (cons [ix iy] res))))))]
    (iter 1 '())))

(->> (read-line)
     Integer/parseInt
     queens
     println)



