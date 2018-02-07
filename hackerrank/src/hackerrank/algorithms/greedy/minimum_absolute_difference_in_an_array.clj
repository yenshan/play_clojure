;;
;; https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem
;;
(ns hackerrank.algorithms.greedy.minimum-absolute-difference-in-an-array
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [_ (read-line)
      nums (str->nums (read-line))
      dat (partition 2 1 (sort nums))
      fpair (first dat)
      min-diff (reduce (fn [res [a b]]
                              (let [d (Math/abs (- a b))]
                              (if (> res d) d res)))
                       (Math/abs (- (first fpair) (second fpair)))
                       (rest dat))
      ]
  (println min-diff)
  )

