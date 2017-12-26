;;
;; https://www.hackerrank.com/challenges/flatland-space-stations/problem
;;
(ns hackerrank.algorithms.implementation.flatland-space-stations
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [[n m] (str->nums (read-line))
      stations (sort (str->nums (read-line)))
      first-d (first stations)
      last-d (- (dec n) (last stations))
      dist-stat (map (fn [[a b]] (quot (- b a) 2)) (partition 2 1 stations))
      max-distance (max first-d
                        last-d 
                        (if (empty? dist-stat)
                          0
                          (apply max dist-stat)))
      ]
  (println max-distance))
