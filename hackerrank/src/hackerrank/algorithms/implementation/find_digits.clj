;;
;; https://www.hackerrank.com/challenges/find-digits/problem
;;
(ns hackerrank.algorithms.implementation.find-digits
  (:require [clojure.string :as s]))


(defn divisable? [dn n]
  (and (not (zero? dn))
       (zero? (mod n dn))))

(defn digit-seq [n]
  (cons (mod n 10) 
        (when (>= n 10) (digit-seq (quot n 10)))))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [N (Integer/parseInt (read-line))]
      (->> (digit-seq N)
           (filter #(divisable? % N))
           count
           println))))
