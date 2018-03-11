;;
;; https://www.hackerrank.com/challenges/absolute-permutation/problem
;;
(ns hackerrank.algorithms.implementation.absolute-permutation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn print-nums [i k]
  (doseq [n (range i (+ i k))]
    (print n)
    (when (< n (dec (+ i k))) (print " "))
    ))

(defn print-abs-permutation [n k]
  (loop [i 1]
    (when (<= i n)
      (when (not= 1 i) (print " "))
      (print-nums (+ i k) k)
      (print " ")
      (print-nums i k)
      (recur (+ i k k))
    ))
  (println ""))

(defn print-seq [coll]
  (let [a (to-array coll)
        len (count coll)]
    (doseq [i (range len)]
      (print (get a i))
      (if (= i (dec len))
        (println "")
        (print " ")))))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [[n k] (str->nums (read-line))]
      (cond (= k 0) (print-seq (range 1 (inc n)))
            (odd? n) (println -1)
            (not= 0 (mod n k)) (println -1)
            (odd? (/ n k)) (println -1)
            :else (print-abs-permutation n k)))))

