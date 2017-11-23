;;
;; https://www.hackerrank.com/challenges/magic-square-forming/problem
;;
(ns hackerrank.algorithms.implementation.forming-a-magic-square2
  (:require [clojure.string :as s]))

;;
;; This problem is not easy if you don't use cheat solution.
;;

(defn sum-when [f coll]
  (->> (map-indexed vector coll)
       (filter (fn [[i _]] (f i)))
       (map second)
       (reduce +)
       ))

(def all-ptn-sum-15 (for [x (range 1 10)
                          y (range 1 10)
                          z (range 1 10)
                          :when (= 15 (+ x y z))]
                      [x y z]))
(defn is-row? [r]
  (fn [n] (= r (mod n 3))))

(def all-ptn-magic-square
  (for [row1 all-ptn-sum-15
        row2 all-ptn-sum-15
        row3 all-ptn-sum-15
        :let [d (concat row1 row2 row3)]
        :when (and 
                (= 9 (count (set d))) ; every digit should be uniq 
                (= 15 (sum-when (is-row? 0) d)) ; col 0
                (= 15 (sum-when (is-row? 1) d)) ; col 1
                (= 15 (sum-when (is-row? 2) d)) ; col 2
                (= 15 (sum-when #(or (= % 2) (= % 4) (= % 6)) d)) ; diagonal /
                (= 15 (sum-when #(= 0 (mod % 4)) d)) ; diagonal \
                )]
    d))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def input-dat (apply concat
                (for [_ (range 3)] 
                  (str->nums (read-line)))))

(->> (for [d all-ptn-magic-square]
       (reduce + (map (fn [a b] (Math/abs (- a b))) input-dat d)))
     sort
     first
     println
     )

