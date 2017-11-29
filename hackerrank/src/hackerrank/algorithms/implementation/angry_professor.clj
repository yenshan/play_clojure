;;
;; https://www.hackerrank.com/challenges/angry-professor/problem
;;
(ns hackerrank.algorithms.implementation.angry-professor
  (:require [clojure.string :as s]))

;; too easy

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn do-judge-okng []
  (let [[_ K] (str->nums (read-line))
        st-times (str->nums (read-line))
        on-time-studens (->> st-times
                             (filter #(<= % 0))
                             count)]
    (if (>= on-time-studens K)
      (println "NO")
      (println "YES"))))

(def T (Integer/parseInt (read-line)))

(doseq [_ (range T)]
  (do-judge-okng))
