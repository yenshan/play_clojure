;;
;; https://www.hackerrank.com/challenges/super-digit/problem
;;
(ns hackerrank.functional-programming.recursion.super-digit
  (:require [clojure.string :as s]))

(defn digit-sum [number]
  (loop [n number, sum 0]
    (if (zero? n)
      sum
      (recur (quot n 10)
             (+ sum (mod n 10))))))

(defn super-digit [number]
  (if (< number 10)
    number
    (recur (digit-sum number))))

(defn num->str-and-sum [nstr]
  (loop [s nstr, res 0]
    (if (empty? s)
      (bigint res)
      (recur (rest s)
             (+ res (Integer/parseInt (str (first s))))))))

(defn parse-and-sum-input [s]
  (let [[nstr kstr] (s/split s #" ")
        n (num->str-and-sum nstr)
        k (Integer/parseInt kstr)
        ]
    (* n k)))

(->> (read-line)
     parse-and-sum-input
     super-digit
     str
     println)

     
