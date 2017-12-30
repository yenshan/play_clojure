;;
;; https://www.hackerrank.com/challenges/manasa-and-stones/problem
;;
(ns hackerrank.algorithms.implementation.manasa-and-stones
  (:require [clojure.string :as s]))

;;
;; not difficult good problem.
;; You need changing point of view to solve this in O(n).
;;

(defn last-nums [n a b]
  (->> (for [i (range n)]
         [i (- n 1 i)])
       (map (fn [[na nb]]
              (+ (* na a) (* nb b))))
       set
       sort))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [n (Integer/parseInt (read-line))
          a (Integer/parseInt (read-line))
          b (Integer/parseInt (read-line))]
      (loop [[a & rst] (last-nums n a b)]
        (when a
          (do (print a)
              (when (not (empty? rst))
                (print " "))
              (recur rst))))
      (println "")
      )))

