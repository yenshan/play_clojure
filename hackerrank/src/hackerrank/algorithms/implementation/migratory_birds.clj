;;
;; https://www.hackerrank.com/challenges/migratory-birds/problem
;;
(ns hackerrank.algorithms.implementation.migratory-birds
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn most-common-bird [coll]
  (->> coll
       sort
       (reduce (fn [res d]
                 (if (get res d)
                   (update res d inc)
                   (assoc res d 1)))
               {})
       vec
       (sort-by (fn [[_ b]] b) #(compare %2 %1))
       first first))

(def _ (read-line))

(-> (read-line)
    str->nums
    most-common-bird
    println)
