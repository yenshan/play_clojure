;;
;; https://www.hackerrank.com/challenges/strange-code/problem
;;
(ns hackerrank.algorithms.implementation.strange-counter
  (:require [clojure.string :as s]))


(defn number-at [n]
  (loop [i 0, sum 0N]
    (println i sum)
    (let [len (* 3 (Math/pow 2 i))
          next-sum (+ sum len)]
      (if (<= n next-sum)
        (bigint (- len (- n sum 1)))
        (recur (inc i)
               next-sum)))))

(let [n (read-string (read-line))]
  (println (str (number-at n))))
