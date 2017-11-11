;;
;; https://www.hackerrank.com/challenges/staircase/problem
;;
(ns hackerrank.algorithms.staircase
  (:require [clojure.string :as s]))


(def N (Integer/parseInt (read-line)))

(doseq [i (range 1 (inc N))]
  (println (apply str (concat (repeat (- N i) \space)
                              (repeat i \#)))))
