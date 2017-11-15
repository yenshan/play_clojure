;;
;; https://www.hackerrank.com/challenges/breaking-best-and-worst-records/problem
;;
(ns hackerrank.algorithms.implementation.breaking-the-record
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn count-if [f coll]
  (:cnt
    (reduce (fn [{:keys [value cnt] :as sum} n]
              (if (f value n)
                {:value n :cnt (inc cnt)}
                sum))
            {:value (first coll) :cnt 0}
            (rest coll))))

(def _ (read-line))

(let [dat (str->nums (read-line))
      most-cnt (count-if #(< %1 %2) dat)
      least-cnt (count-if #(> %1 %2) dat)]
  (println most-cnt least-cnt))
       

