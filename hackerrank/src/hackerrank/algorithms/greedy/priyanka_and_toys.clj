;;
;;https://www.hackerrank.com/challenges/priyanka-and-toys/problem
;;
(ns hackerrank.algorithms.greedy.priyanka-and-toys
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn pop-container [[x & xs]]
  (drop-while #(<= % (+ x 4)) xs))

(let [_ (read-line)
      weights (sort (str->nums (read-line)))
      ctns (loop [wes weights
                  cnt 0]
             (if (empty? wes)
               cnt
               (recur (pop-container wes) (inc cnt))))
      ]
  (println ctns))
