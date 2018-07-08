;;
;; https://www.hackerrank.com/challenges/most-distant/problem
;;
(ns hackerrank.mathmatics.fundamentals.most-distant
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn distance [[[x1 y1] [x2 y2]]]
  (let [dx (Math/abs (- x1 x2))
        dy (Math/abs (- y1 y2))]
    (Math/sqrt (+ (* dx dx) (* dy dy)))))

(defn combination [coll]
  (loop [[x & xs] coll
         res []]
  (if (nil? x)
    res
    (recur xs 
           (concat res (for [y xs] [x y]))))))

(defn get-max-min [coll]
  (loop [[[x y :as p] & ps] coll
         max-x 0, min-x 0, max-y 0, min-y 0]
    (if (nil? p)
      [max-x, min-x, max-y, min-y]
      (recur ps
             (if (> x max-x) x max-x)
             (if (< x min-x) x min-x)
             (if (> y max-y) y max-y)
             (if (< y min-y) y min-y)))))

(let [N (Integer/parseInt (read-line))
      dats (for [_ (range N)] (str->nums (read-line)))
      [max-x min-x max-y min-y] (get-max-min dats)
      most-dist (->> (combination [[max-x 0]
                                   [min-x 0]
                                   [0 max-y]
                                   [0 min-y]])
                     (map distance)
                     (apply max))
      ]
  (println most-dist))


