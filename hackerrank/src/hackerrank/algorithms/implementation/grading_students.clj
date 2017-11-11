(ns hackerrank.algorithms.implementation.grading-students
  (:require [clojure.string :as s]))

(defn grade [score]
  (if (< score 38)
    score
    (let [tn (mod score 10)
          ttn (if (> tn 5) (- tn 5) tn)
          diff (- 5 ttn)
          ]
      (if (< diff 3) (+ score diff) score))))

(def n (Integer/parseInt (read-line)))

(doseq [_ (range n)]
  (println (grade (Integer/parseInt (read-line)))))
