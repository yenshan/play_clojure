(ns hackerrank.algorithms.implementation.apple-and-orange
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [[s t] (str->nums (read-line))
      [a b] (str->nums (read-line))
      [m n] (str->nums (read-line))
      da (str->nums (read-line))
      db (str->nums (read-line))
      ada (map #(+ a %) da)
      adb (map #(+ b %) db)
      n-a (filter #(<= s % t) ada)
      n-b (filter #(<= s % t) adb)
      ]
  (println (count n-a))
  (println (count n-b)))

