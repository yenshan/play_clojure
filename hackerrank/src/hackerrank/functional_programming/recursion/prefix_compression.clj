;;
;; https://www.hackerrank.com/challenges/prefix-compression/problem
;;
(ns hackerrank.functional-programming.recursion.prefix-compression
  (:require [clojure.test :refer :all]))

(defn get-prefix [str1 str2]
  (loop [s1 str1, s2 str2, res []]
    (if (or (empty? s1) (empty? s2)
            (not= (first s1) (first s2)))
      (map #(apply str %) (list res s1 s2))
      (recur (rest s1) (rest s2) (conj res (first s1))))))

(doseq [i (get-prefix (read-line) (read-line))]
  (println (count i) i))


