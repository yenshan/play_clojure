;;
;;https://www.hackerrank.com/challenges/grid-challenge/problem
;;
(ns hackerrank.algorithms.greedy.grid-challenge
  (:require [clojure.string :as s]))

(defn ascent? [coll]
  (= (sort coll) coll))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [n (Integer/parseInt (read-line))
          grids (for [_ (range n)]
                  (read-line))
          cols (count (first grids))
          row-sorted (map sort grids)
          col-arrays (for [i (range cols)]
                       (map #(nth % i)
                            row-sorted))
          res (every? true? 
                      (map ascent? col-arrays))
          ]
      (println (if res "YES" "NO")))))


