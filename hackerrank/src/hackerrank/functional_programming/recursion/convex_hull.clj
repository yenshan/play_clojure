;;
;; https://www.hackerrank.com/challenges/convex-hull-fp/problem
;;
(ns hackerrank.functional-programming.recursion.convex-hull
  (:require [clojure.string :as s]))

(defn get-most-left-pos [coll]
  (loop [c (rest coll), res (first coll)]
    (if (empty? c)
      res
      (recur (rest c)
             (let [e (first c)
                   [x1 _] res
                   [x2 _] e]
               (if (< x2 x1) e res))))))

(defn get-vector [[x1 y1] [x2 y2]] [(- x2 x1) (- y2 y1)])

(defn cross-product
  [[a b] [c d]]
  (- (* a d) (* b c)))

(defn left-convex-poit [pos coll]
  (->> (remove #(= % pos) coll)
       (map (fn [p] 
              {:pos p :vec (get-vector pos p)})) 
       (sort (fn [e1 e2] 
               (if (> 0 (cross-product (:vec e1) (:vec e2))) true false)))
       first
       (:pos)
       ))

(defn get-convex-points [coll]
  (let [first-p (get-most-left-pos coll)]
    (loop [p first-p, res [first-p]]
      (let [next-p (left-convex-poit p coll)]
        (if (= next-p first-p)
          res
          (recur next-p (conj res next-p)))))))


(defn perimeter
  [[x1 y1] [x2 y2]]
  (Math/sqrt (+ (Math/pow (- x2 x1) 2)
                (Math/pow (- y2 y1) 2))))

(defn perimeter-around 
  "coll should be vector"
  [coll]
  (loop [d (conj coll (first coll)),
         sum 0]
    (if (< (count d) 2)
      sum
      (recur (rest d)
             (+ sum (perimeter (first d) (second d)))))))

(defn convex_hull [coll]
  (->> (get-convex-points coll)
       vec
       perimeter-around))

(defn num->vec [nstr]
  (->> (s/split nstr #" ")
       (map #(Integer/parseInt %))))

(def N (Integer/parseInt (read-line)))

(->> (for [_ (range N)]
       (num->vec (read-line)))
     convex_hull
     println)

