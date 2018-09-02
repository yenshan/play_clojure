;;
;; https://www.hackerrank.com/challenges/runningtime/problem
;;
(ns hackerrank.algorithms.sorting.running-time-of-algorithm
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(defn pickup-nth [i coll]
  [(nth coll i)
   (take i coll)
   (drop (inc i) coll)
   ])

(defn isort [v coll]
  (loop [[x & xs] coll
         res []
         cnt 0
         ]
    (cond (nil? x) {:dat (conj res v) :cnt 0}
          (< v x) {:dat (concat res [v x] xs) :cnt (- (count coll) cnt)}
          :else (recur xs
                       (conj res x)
                       (inc cnt)))))

(defn sort-loop [i cnt dat shift-cnt]
  (if (>= i cnt)
    shift-cnt
    (let [[v h t] (pickup-nth i dat)
          _sortd (isort v h)
          nxt (concat (:dat _sortd) t)]
      (recur (inc i)
             cnt
             nxt
             (+ shift-cnt (:cnt _sortd))))))

(let [_ (read-line)
      dat (str->nums (read-line))]
  (println (sort-loop 1 (count dat) dat 0)))
