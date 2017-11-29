;;
;; https://www.hackerrank.com/challenges/beautiful-days-at-the-movies/problem
;;
(ns hackerrank.algorithms.implementation.beautiful-days-and-the-movie
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn reverse-num [n]
  (->> (str n)
       reverse
       (apply str)
       Integer/parseInt))

(defn is-beautiful-day? [^long n ^long k]
  (let [r-n (long (reverse-num n))]
    (zero? (mod (Math/abs (- n r-n)) k))))

(let [[i j k] (str->nums (read-line))]
  (->> (map #(is-beautiful-day? % k) (range i (inc j)))
       (filter true?)
       count
       println))
