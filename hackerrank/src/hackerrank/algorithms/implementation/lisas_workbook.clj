;;
;; https://www.hackerrank.com/challenges/lisa-workbook/problem
;;
(ns hackerrank.algorithms.implementation.lisas-workbook
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn -partition [k coll]
  (loop [d coll, res []]
    (if (empty? d)
      res
      (recur (drop k d)
             (conj res (set (take k d)))))))

(let [[_ k] (str->nums (read-line))]
  (->> (str->nums (read-line))
       (map #(range 1 (inc %)))
       (map #(-partition k %))
       (apply concat)
       (map-indexed #(vector (inc %1) %2))
       (filter (fn [[i d]] (contains? d i)))
       count
       println))

