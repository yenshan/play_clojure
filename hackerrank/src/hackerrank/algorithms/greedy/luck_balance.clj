;;
;;https://www.hackerrank.com/challenges/luck-balance/problem
;;
(ns hackerrank.algorithms.greedy.luck-balance
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(let [[n k] (str->nums (read-line))
      lbt (->> (for [_ (range n)]
                 (str->nums (read-line)))
                 (sort-by first)
                 (sort-by second #(compare %2 %1)))
      important (count (filter (fn [[_ d]] (= d 1)) lbt))
      must-win (- important k)
      minus (->> (take must-win lbt)
                 (reduce (fn [s [l t]] (+ s l)) 0N))
      lucks (->> (drop must-win lbt)
                (reduce (fn [s [l t]] (+ s l)) 0N))
      ]
  (println (str (- lucks minus))))
