;;
;; https://www.hackerrank.com/challenges/halloween-sale/problem
;;
(ns hackerrank.algorithms.implementation.halloween-sale
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn sale-price-seq [start d m]
  (lazy-seq (cons (if (<= start m) m start) 
                  (sale-price-seq (- start d) d m))))

(let [[p d m s] (str->nums (read-line))
      res (loop [[a & rst] (sale-price-seq p d m)
                 sum 0
                 cnt 0]
            (if (> (+ a sum) s)
              cnt
              (recur rst (+ a sum) (inc cnt))))
      ]
     (println res))
