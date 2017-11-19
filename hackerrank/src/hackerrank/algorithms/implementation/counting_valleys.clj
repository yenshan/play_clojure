;;
;; https://www.hackerrank.com/challenges/counting-valleys/problem
;;
(ns hackerrank.algorithms.implementation.counting-valleys
  (:require [clojure.string :as s]))

(def _ (read-line))

;;
;; This implementation didn't pass because timeout. But I like this implementation
;;
(->> (read-line) 
     (map #(if (= % \U) + -))
     (reduce (fn [res f]
               (conj res (f (last res) 1)))
             [0])
     (partition 2 1)
     (filter #(= % '(-1 0)))
     count
     println)
