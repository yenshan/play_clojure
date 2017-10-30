(ns hackerrank.clj.super-queen-on-chessboard2
  (:require [clojure.test :refer :all]))

(def empty-board '())

(defn same-row? [pos1 pos2]
  (= (second pos1) (second pos2)))

(defn conflict-diagonally? 
  [[x1 y1] [x2 y2]]
  (let [tmp (double (/ (- y2 y1) (- x2 x1)))]
    (or (= 1.0 tmp) (= -1.0 tmp))))

(defn conflict-in-box?
  [[x1 y1] [x2 y2]]
  (and (>= x2 (- x1 2))
       (>= y2 (- y1 2))
       (<= y2 (+ y1 2))))

(defn safe? [k positions]
  (let [nq (first positions)]
    (= nil 
       (some #(or (same-row? nq %)
                  (conflict-in-box? nq %)
                  (conflict-diagonally? nq %)
                  )
             (rest positions)))))

(defn adjoin-position [row col coll]
  (cons [col row] coll))

;;
;; this solutoin takes 20 seconds when N = 14
;;

;;
;; SCIP's 8 queens solution
;;
(defn queens [board-size]
  (letfn [(queen-cols [k]
            (if (zero? k)
              (list empty-board)
              (filter
                (fn [positions] (safe? k positions))
                (apply concat 
                       (map
                         (fn [rest-of-queens]
                           (map (fn [new-row]
                                  (adjoin-position new-row k rest-of-queens))
                                (range 1 (inc board-size))))
                         (queen-cols (dec k)))))))]
    (queen-cols board-size)))

(defn clj-queens [board-size]
  (letfn [(queen-cols [k]
            (if (zero? k)
              (list empty-board)
              (for [rest-of-queens (queen-cols (dec k))
                    new-row (range 1 (inc board-size))
                    :let [elem (adjoin-position new-row k rest-of-queens)]
                    :when (safe? k elem)]
                elem)))]
    (queen-cols board-size)))

(->> (read-line)
     Integer/parseInt
     double
     clj-queens
     count
     println)

