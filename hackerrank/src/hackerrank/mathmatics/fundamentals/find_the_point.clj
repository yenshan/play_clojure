;;
;; https://www.hackerrank.com/challenges/find-point/problem
;;
(ns hackerrank.mathmatics.fundamentals.find-the-point
  (:require [clojure.string :as s]))

(defn get-vector
  [[x1 y1] [x2 y2]]
  [(- x2 x1) (- y2 y1)])

(defn add-vector 
  [[px py] [vx vy]]
  [(+ px vx) (+ py vy)])

(defn print-pos [[x y]]
  (println x y))

(defn find-the-point
  [p q]
  (add-vector q (get-vector p q)))

(defn str->num [string]
  (->> (map #(Integer/parseInt %) (s/split string #" "))
       (partition 2 2)))

(def n (Integer/parseInt (read-line)))

(doseq [_ (range n)]
  (let [[p q] (str->num (read-line))]
    (print-pos (find-the-point p q))))

