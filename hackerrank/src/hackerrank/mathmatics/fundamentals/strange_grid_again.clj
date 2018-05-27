;;
;; https://www.hackerrank.com/challenges/strange-grid/problem
;;
(ns hackerrank.mathmatics.fundamentals.strange-grid-again
  (:require [clojure.string :as s]))


(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn find-int [row col]
  (let [r (dec row)
        c (dec col)]
    (if (odd? r)
      (let [idx (+ c 
                   (* 5 (quot (- r 1)
                              2)))]
        (+ 1 (* 2 idx)))
      (let [idx (+ c
                   (* 5 (quot r 2)))]
        (* 2 idx)))))

(let [[r c] (str->nums (read-line))]
  (println (find-int r c)))
