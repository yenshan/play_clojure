;;
;; https://www.hackerrank.com/challenges/common-divisors/problem
;;
(ns hackerrank.functional-programming.ad-hoc.common-divisors
  (:require [clojure.string :as s]))

;;
;; Good problem. You shoule optimize algorithm for performance.
;;

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))

(defn count-divisors [maxn f b]
  (loop [n 1, cnt 0]
    (if (> n (quot maxn b))
      cnt
      (recur (f n)
             (+ cnt (if (zero? (mod maxn n)) 1 0))))))

(defn count-common-divisors [n1 n2]
  (let [ngcd (gcd (max n1 n2) (min n1 n2))]
    (+ 1
       (if (odd? ngcd)
         (count-divisors ngcd #(+ % 2) 3)
         (count-divisors ngcd inc 2)))))

(defn -run []
  (let [T (Integer/parseInt (read-line))]
    (doseq [_ (range T)]
      (let [[M R] (str->nums (read-line))]
        (println (count-common-divisors M R))))))

(-run)
