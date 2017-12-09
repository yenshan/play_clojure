;;
;; https://www.hackerrank.com/challenges/library-fine/problem
;;
(ns hackerrank.algorithms.implementation.library-fine
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [[rd rm ry] (str->nums (read-line))
      [ed em ey] (str->nums (read-line))
      fine (cond (> ry ey) 10000
                 (< ry ey) 0
                 (> rm em) (* 500 (- rm em))
                 (< rm em) 0
                 (> rd ed) (* 15 (- rd ed))
                 :else 0)]
  (println fine))
      
