;;
;; https://www.hackerrank.com/challenges/beautiful-triplets/problem
;;
(ns hackerrank.algorithms.implementation.beautiful-triplet
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn get-pairs [^long d coll]
  (for [i (range (count coll))]
    (let [n1 (get coll i)]
    (loop [j (inc i), res nil]
      (if (or (>= j (count coll)) (> j (+ i d)))
        res
        (let [n2 (get coll j)]
          (if (= d (- n2 n1))
            [n1 n2]
            (recur (inc j) res))))))))
                   
(let [[_ d] (str->nums (read-line))
      dat (to-array (str->nums (read-line)))
      pairs (get-pairs d dat)
      m-pairs (reduce (fn [res d]
                        (assoc res (second d) d))
                      {}
                      pairs)
      n-triplets (reduce (fn [res d]
                       (if (m-pairs (first d))
                         (inc res)
                         res))
                     0
                     pairs)
      ]
  (println n-triplets)
  )
      

