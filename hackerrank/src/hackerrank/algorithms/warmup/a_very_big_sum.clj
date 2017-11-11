;;
;; https://www.hackerrank.com/challenges/a-very-big-sum/problem
;;
(ns hackerrank.algorithms.warmup.a-very-big-sum
  (:require [clojure.string :as s]))

(defn str->bignums [str]
  (->> (s/split str #" ")
       (map #(bigint (Integer/parseInt %)))))

(def N (Integer/parseInt (read-line)))

(->> (str->bignums (read-line))
     (reduce +)
     str
     println)
  
