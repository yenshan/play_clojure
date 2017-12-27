;;
;; https://www.hackerrank.com/challenges/fair-rations/problem
;;
(ns hackerrank.algorithms.implementation.fair-rations
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [n (Integer/parseInt (read-line))
      dats (str->nums (read-line))
      idats (map-indexed vector dats)
      odds (filter #(odd? (second %)) idats)
      ]
  (if (odd? (count odds))
    (println "NO")
    (let [idxs (partition 2 (map first odds))
          min-loaves (reduce + (map (fn [[a b]] (* 2 (- b a))) idxs))]
      (println min-loaves))))

