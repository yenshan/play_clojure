;;
;; https://www.hackerrank.com/challenges/divisible-sum-pairs/problem
;;
(ns hackerrank.algorithms.implementation.divisible-sum-pair
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn count-divisible-sum-pair [k dat]
  (loop [[a & rst] dat, ret 0]
    (if (empty? rst)
      ret
      (recur rst (->> rst
                      (filter #(zero? (mod (+ % a) k))) 
                      count
                      (+ ret))))))

(let [[_ k] (str->nums (read-line))
      dat (str->nums (read-line))]
  (println (count-divisible-sum-pair k dat)))
        

