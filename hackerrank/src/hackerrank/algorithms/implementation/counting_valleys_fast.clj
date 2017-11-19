;;
;; https://www.hackerrank.com/challenges/counting-valleys/problem
;;
(ns hackerrank.algorithms.implementation.counting-valleys-fast
  (:require [clojure.string :as s]))

(def _ (read-line))

(->> (read-line) 
     (reduce (fn [res in]
               (let [pre-d (:pre-d res)
                     new-d (if (= in \U) (inc pre-d) (dec pre-d))
                     cnt (if (and (zero? new-d) (= -1 pre-d))
                           (inc (:cnt res))
                           (:cnt res))]
                 {:cnt cnt :pre-d new-d }))
             {:cnt 0 :pre-d 0})
     :cnt
     println)
