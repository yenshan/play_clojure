;;
;; https://www.hackerrank.com/challenges/designer-pdf-viewer/problem
;;
(ns hackerrank.algorithms.implementation.designer-pdf-viewer
  (:require [clojure.string :as s]))

(defn abc-height-map [coll]
  (let [abc "abcdefghijklmnopqrstuvwxyz"]
    (->> (map (fn [a b] [a b]) abc coll)
         (reduce (fn [res [k d]]
                   (assoc res k d))
                 {}))))
(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [heights (str->nums (read-line))
      word (read-line)
      h-map (abc-height-map heights)
      max-h (->> word
                 (map #(get h-map %))
                 (apply max))]
  (println (* max-h (count word))))

