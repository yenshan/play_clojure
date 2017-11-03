(ns hackerrank.clj.convex-hull
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


(defn slope [[x1 y1] [x2 y2]] 
  (let [dx (- x2 x1)
          dy (- y2 y1)]
    (if (zero? dx)
      (double (/ dy 0.00001))
      (double (/ dy dx)))))

(defn find-convex-pos [pos1 pos2 coll]
  (let [rest-coll (remove #(or (= % pos1) (= % pos2)) coll)]
    (->> (for [e rest-coll]
                 {:pos e :slope (slope pos2 e)})
         (sort #(compare (:slope %2) (:slope %1)))
         first
         :pos
         )))

(defn get-convex-points [coll]
  (let [first-p (get-most-left-pos coll)]
    (loop [p1 nil
           p2 first-p
           res [first-p]]
      (let [next-p (find-convex-pos p1 p2 coll)]
        (if (= next-p first-p)
          res
          (recur p2
                 next-p
                 (conj res next-p)))))))

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

