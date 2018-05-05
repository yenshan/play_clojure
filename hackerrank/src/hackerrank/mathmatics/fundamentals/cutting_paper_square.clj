(ns hackerrank.mathmatics.fundamentals.cutting-paper-square
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(let [[n m] (str->nums (read-line))]
  (println (+ (- n 1) 
              (* (- m 1) n))))
