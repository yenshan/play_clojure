;;
;; https://www.hackerrank.com/challenges/the-hurdle-race/problem
;;
(ns hackerrank.algorithms.implementation.the-hurdle-race
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn max-value [coll]
  (loop [[a & rst] coll, maxv 0]
    (if (nil? a)
      maxv
      (recur rst
             (if (< maxv a) a maxv)))))

(let [[_ k] (str->nums (read-line))
      hurdles (str->nums (read-line))
      maxv (max-value hurdles)]
  (println (if (< k maxv) (- maxv k) 0)))
