;;
;; https://www.hackerrank.com/challenges/permutation-equation/problem
;;
(ns hackerrank.algorithms.implementation.sequence-quation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [N (Integer/parseInt (read-line))
      dat (str->nums (read-line))
      rev-map (->> (map-indexed #(vector (inc %1) %2) dat)
                   (reduce (fn [res [i d]]
                             (assoc res d i))
                           {}))]
  (doseq [i (range 1 (inc N))]
    (println (rev-map (rev-map i)))))
