;;
;; https://www.hackerrank.com/challenges/3d-surface-area/problem
;;
(ns hackerrank.algorithms.implementation.3d-surface-area
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(defn count-adjacent-blocks [coll n]
  (loop [[a & rst] coll
         preb 0
         cnt 0]
    (cond (nil? a) cnt
          (and (>= a n) (>= preb n)) (recur rst a (inc cnt))
          :else (recur rst a cnt))))


(let [[H W] (str->nums (read-line))
      area-rows (for [_ (range H)]
                  (str->nums (read-line)))
      area-cols (for [c (range W)]
                  (map #(nth % c) area-rows))
      depth (apply max (apply concat area-rows))
      cnts-depth (map dec (apply concat area-rows))
      cnts-wh (for [n (range 1 (inc depth))]
                (reduce + (map #(count-adjacent-blocks % n) 
                               (concat area-rows area-cols))))
      cnts (reduce + (concat cnts-depth cnts-wh))
      all-sfs (* 6 (reduce + (apply concat area-rows)))
      ]
  (println (- all-sfs (* cnts 2)))
  )
