;;
;; https://www.hackerrank.com/challenges/palindrome-index/problem
;;
(ns hackerrank.algorithms.string.palindrome-index
  (:require [clojure.string :as s]))


(defn palindrome? [s]
  (let [hlen (quot (count s) 2)
        pre-half (subs s 0 hlen)
        rear-half (apply str (take hlen (reverse s)))]
    (loop [[a & rst1] pre-half
           [b & rst2] rear-half
           i 0]
      (cond (nil? a) -1
            (not= a b) i
            :else (recur rst1 rst2 (inc i))))))

(defn delete-char-at [s i]
  (-> s
      StringBuilder.
      (.deleteCharAt i)
      .toString))

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [string (read-line)
          len (count string)
          pi (palindrome? string)
          ]
      (cond (= -1 pi) (println -1)
            (= -1 (palindrome? (delete-char-at string pi))) (println pi)
            (= -1 (palindrome? (delete-char-at string (- len pi 1)))) (println (- len pi 1))
            :else (println -1))
      )))

