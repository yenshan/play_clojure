;;
;; https://www.hackerrank.com/challenges/lonely-integer/problem
;;
(ns hackerrank.algorithms.bit-manipulation.lonely-integer
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [_ (read-line)
      nums (str->nums (read-line))]
  (->> nums
       (reduce (fn [res d]
                         (if (get res d)
                           (update res d inc)
                           (assoc res d 1)))
                       {})
       (filter (fn [[a b]] (= b 1)))
       first
       first
       (println)))
