;;
;; https://www.hackerrank.com/challenges/mini-max-sum/problem
;;
(ns hackerrank.algorithms.warmup.mini-max-sum
  (:require [clojure.string :as s]))

(defn drop-nth [n coll]
  (->> (map (fn [a b] [a b]) (range (count coll)) coll)
       (filter (fn [[i _]] (not= i n)))
       (map (fn [[_ a]] a))))

(defn sum-pattern [n coll]
  (letfn [(iter [n c res]
            (if (zero? n)
              (reduce + res)
              (let [ret (for [i (range (count c))]
                          (iter (dec n)
                                (drop-nth i c)
                                (conj res (nth c i))))]
                (if (> n 1)
                  (apply concat ret)
                  ret))))]
    (iter n coll [])))

(defn print-result [coll]
  (println (str (first coll)) (str (last coll))))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(bigint (Integer/parseInt %)))))

(->> (sum-pattern 4 (str->nums (read-line)))
     set
     sort
     print-result)
