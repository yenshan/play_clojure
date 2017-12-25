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

(let [[n k] (str->nums (read-line))
      dat (str->nums (read-line))
      problems (map (fn [nn]
                      (take nn (iterate inc 1)))
                    dat)
      pages (apply concat (map #(-partition k %) problems))
      page-indexed (map-indexed #(vector (inc %1) %2) pages)
      specials (filter (fn [[i d]] (contains? d i)) page-indexed)
      ]
  (println (count specials))
  )

