;;
;; https://www.hackerrank.com/challenges/strange-advertising/problem
;;
(ns hackerrank.algorithms.implementation.viral-advertising
  (:require [clojure.string :as s]))

(defn viral-adv [n]
  (lazy-seq (cons n (viral-adv (* 3 (quot n 2))))))

(let [n (Integer/parseInt (read-line))]
  (->> (take n (viral-adv 5))
       (map #(quot % 2))
       (reduce +)
       println))

