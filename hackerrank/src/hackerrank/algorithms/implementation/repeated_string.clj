;;
;; https://www.hackerrank.com/challenges/repeated-string/problem
;;
(ns hackerrank.algorithms.implementation.repeated-string
  (:require [clojure.string :as s]))

(defn cnt-char-until [c n coll]
  (loop [[a & rst] coll, i 0, res 0]
    (cond (or (>= i n) (nil? a)) res
          (= a c) (recur rst (inc i) (inc res))
          :else (recur rst (inc i) res))))

(defn str->num [string]
  (loop [[a & rst] (reverse string), i 1, res 0N]
    (if (nil? a)
      res
      (recur rst
             (* i 10)
             (+ res (* i (Integer/parseInt (str a))))))))

(let [string (read-line)
      n (str->num (read-line))
      len (count string)
      a-in-str (cnt-char-until \a len string)
      times (quot n len)
      a-in-rem-str (cnt-char-until \a (mod n len) string)
      ]
  (println (str (+ a-in-rem-str (* a-in-str times)))))
