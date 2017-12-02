;;
;; https://www.hackerrank.com/challenges/jumping-on-the-clouds-revisited/problem
;;
(ns hackerrank.algorithms.implementation.jumping-to-the-clouds
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn sum-of-cosumed-energy [n k clouds]
  (loop [i k, res 0]
    (let [ce (if (zero? (get clouds i)) 1 3)]
      (if (zero? i)
        (+ res ce)
        (recur (mod (+ i k) n)
               (+ res ce))))))

(let [[n k] (str->nums (read-line))
      clouds (to-array (str->nums (read-line)))]
  (println (- 100 (sum-of-cosumed-energy n (mod k n) clouds))))
  

