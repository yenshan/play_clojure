;;
;; https://www.hackerrank.com/challenges/day-of-the-programmer/problem
;;
(ns hackerrank.algorithms.implementation.day-of-the-programmer
  (:require [clojure.string :as s]))

(def days [31 28 31 30 31 30 31 31 30 31 30 31])

(defn is-leap-year? [y]
  (if (<= y 1918)
    (zero? (mod y 4))
    (or (zero? (mod y 400))
        (and (zero? (mod y 4))
             (not= 0 (mod y 100))))))

(def year (Integer/parseInt (read-line)))

(let [days-coll (cond (= year 1918) (update days 1 #(- % 13))
                      (is-leap-year? year) (update days 1 inc)
                      :else days)
      m-coll (loop [[d & rst] days-coll, sum 0, res []]
               (if (>= sum 256)
                 res
                 (recur rst (+ sum d) (conj res d))))
      m (count m-coll)
      d (- 256 (reduce + (drop-last m-coll)))
      ]
  (println (format "%02d.%02d.%04d" d m year)))


