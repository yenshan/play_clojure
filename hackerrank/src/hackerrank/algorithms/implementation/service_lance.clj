;;
;; https://www.hackerrank.com/challenges/service-lane/problem
;;
(ns hackerrank.algorithms.implementation.service-lance
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [[_ T] (str->nums (read-line))
      lanes (vec (str->nums (read-line)))
      ]
  (doseq [_ (range T)]
    (let [[start end] (str->nums (read-line))
          pass (subvec lanes start (inc end))
          ]
      (println (apply min pass)))))
     
