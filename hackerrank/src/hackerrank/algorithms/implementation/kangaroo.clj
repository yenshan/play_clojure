(ns hackerrank.algorithms.implementation.kangaroo
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [[x1 v1 x2 v2] (str->nums (read-line))]
  (if (or (and (> x1 x2) (>= v1 v2))
          (and (< x1 x2) (<= v1 v2)))
    (println "NO")
    (if (zero? (mod (Math/abs (- x1 x2))
                    (Math/abs (- v2 v1))))
      (println "YES")
      (println "NO"))))
