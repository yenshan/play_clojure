;;
;; https://www.hackerrank.com/challenges/halloween-sale/problem
;;
(ns hackerrank.algorithms.implementation.halloween-sale
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn price-list [start d m]
  (lazy-seq (cons (if (<= start m) m start) 
                  (price-list (- start d) d m))))

(defn num-can-buy [s pl]
  (loop [[a & rst] pl, sum 0, cnt 0]
    (if (> (+ a sum) s)
      cnt
      (recur rst (+ a sum) (inc cnt)))))

(let [[p d m s] (str->nums (read-line))]
    (println (num-can-buy s (price-list p d m))))
