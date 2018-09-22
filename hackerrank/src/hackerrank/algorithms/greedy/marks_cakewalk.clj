(ns hackerrank.algorithms.greedy.marks-cakewalk
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn remove-i [i coll]
  (loop [[x & xs] coll
         n 0
         res []]
  (cond (nil? x) res
        (= n i) (recur xs (inc n) res)
        :else (recur xs (inc n) (conj res x)))))

(defn cal-ptns [coll]
  (if (empty? coll)
    [[]]
    (apply concat 
           (for [i (range (count coll))]
             (map #(cons (get coll i) %)
                  (cal-ptns (remove-i i coll)))))))

(defn sum-cals [coll]
  (int (reduce (fn [s [i d]]
                 (+ s (* (Math/pow 2 i)
                         d)))
               0
               (map-indexed vector coll))))

(let [_ (read-line)
      cals (vec (str->nums (read-line)))
     ] 
  (->> (cal-ptns cals)
       (map #(sum-cals %))
       (apply min)
       println))
