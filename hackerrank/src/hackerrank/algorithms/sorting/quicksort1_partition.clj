;;
;;https://www.hackerrank.com/challenges/quicksort1/problem
;;
(ns hackerrank.algorithms.sorting.quicksort1-partition
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn print-arr [coll]
  (loop [[x & xs] coll]
    (when x
      (do 
        (print x)
        (if (nil? xs)
          (println "")
          (print " "))
        (recur xs)))))

(let [_ (read-line)
      arr (str->nums (read-line))
      pivot (first arr)
      ]
  (print-arr (concat (filter #(< % pivot) arr)
                     (filter #(= % pivot) arr)
                     (filter #(> % pivot) arr))))
