(ns hackerrank.algorithms.greedy.marks-cakewalk
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn exp2 [n]
  (if (zero? n)
    1
    (reduce #(* %1 %2) 1 (repeat n 2))))

(defn sum-cals [coll]
  (reduce (fn [s [i d]]
            (+ s (* (exp2 i)
                    d)))
          0N
          (map-indexed vector coll)))

(let [_ (read-line)
      cals (str->nums (read-line))
     ] 
  (->> (sort #(compare %2 %1) cals)
       sum-cals
       str
       println))
