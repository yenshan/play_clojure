;;
;; https://www.hackerrank.com/challenges/equality-in-a-array/problem
;;
(ns hackerrank.algorithms.implementation.equalize-the-array
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [n (Integer/parseInt (read-line))
      array (str->nums (read-line))
      num-cnt (reduce (fn [res d]
                        (if (res d)
                          (update res d inc)
                          (assoc res d 1)))
                      {}
                      array)
      rank-cnt (sort-by second #(compare %2 %1) num-cnt)]
  (println (- n (second (first rank-cnt)))))
