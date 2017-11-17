;;
;; https://www.hackerrank.com/challenges/divisible-sum-pairs/problem
;;
(ns hackerrank.algorithms.implementation.divisible-sum-pair
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn count-if [f coll]
  (reduce (fn [res x] (if (f x) (inc res) res))
          0
          coll))

(defn count-divisible-sum-pair [k dat]
  (loop [a (first dat)
         col (rest dat)
         ret 0]
    (if (empty? col)
      ret
      (recur (first col)
             (rest col)
             (+ ret (count-if #(zero? (mod (+ % a) k)) col))))))

(let [[_ k] (str->nums (read-line))
      dat (str->nums (read-line))]
  (println (count-divisible-sum-pair k dat)))
        

