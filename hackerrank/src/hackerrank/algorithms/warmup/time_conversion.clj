;;
;; https://www.hackerrank.com/challenges/time-conversion/problem
;;
(ns hackerrank.algorithms.warmup.time-conversion
  (:require [clojure.string :as s]))

(defn convert-time [ampm tm]
  (if (= ampm "AM")
    (if (= 12 (first tm))
      (update tm 0 #(- % 12))
      tm)
    (if (= 12 (first tm))
      tm
      (update tm 0 #(+ % 12)))))

(defn print-time [[h m s]]
  (println (format "%02d:%02d:%02d" h m s)))

(def input-time (read-line))

(let [ampm (apply str (take-last 2 input-time))]
  (->> (vec (map #(Integer/parseInt %)
                 (s/split (apply str (drop-last 2 input-time)) #":")))
       (convert-time ampm)
       (print-time)))

    
    
