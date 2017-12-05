;;
;; https://www.hackerrank.com/challenges/cut-the-sticks/problem
;;
(ns hackerrank.algorithms.implementation.cut-the-sticks
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn cut-the-sticks [coll]
  (let [min-len (apply min coll)]
    (->> coll
         (map #(- % min-len))
         (filter #(not (zero? %))))))

(let [_ (Integer/parseInt (read-line))
      sticks (str->nums (read-line))]
  (loop [coll sticks]
    (when (not (empty? coll))
      (do (println (count coll))
          (recur (cut-the-sticks coll))))))

