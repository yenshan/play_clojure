#!/usr/bin/env clj
;;
;; https://www.hackerrank.com/challenges/lambda-march-compute-the-perimeter-of-a-polygon/problem
;;
(ns hackerrank.clj.perimeter-polygon
  (:require [clojure.string :as str]))

(defn square [x] (* x x))

(defn len [p1 p2]
  (let [x1 (first p1)
        y1 (second p1)
        x2 (first p2)
        y2 (second p2)]
    (Math/sqrt (+ (square (- x2 x1)) 
                  (square (- y2 y1))))))

(defn perimeter
  ([coll] (perimeter (conj coll (first coll)) 0))
  ([coll res]
   (if (< (count coll) 2)
     res
     (recur (rest coll)
            (+ res (len (first coll) (second coll)))))))

(defn str->pair [string]
  (let [d (str/split string #" ")]
    [(Integer/parseInt (first d)) (Integer/parseInt (second d))]))

(let [nt (Integer/parseInt (read-line))]
  (let [datas (for [i (range nt)]
                (str->pair (read-line)))]
    (println (perimeter (vec datas)))))
