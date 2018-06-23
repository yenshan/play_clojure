;;
;; https://www.hackerrank.com/challenges/insertionsort2/problem
;;
(ns hackerrank.algorithms.sorting.insertion-sort2
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
         res []]
    (cond (nil? x) (conj res v)
          (<= v x) (concat res [v x] xs) 
          :else (recur xs
                       (conj res x)))))

(defn print-list [[x & xs]]
  (if-not (nil? x)
    (do (print x "")
        (recur xs))
    (println "")))

(defn sort-loop [i cnt dat]
  (when (< i cnt)
    (let [[v h t] (pickup-nth i dat)
          nxt (concat (isort v h) t)
          ]
      (print-list nxt)
      (recur (inc i) cnt nxt))))

(let [_ (read-line)
      dat (str->nums (read-line))]
  (sort-loop 1 (count dat) dat))


