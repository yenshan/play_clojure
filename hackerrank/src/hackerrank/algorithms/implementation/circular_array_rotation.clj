;;
;; https://www.hackerrank.com/challenges/circular-array-rotation/problem
;;
(ns hackerrank.algorithms.implementation.circular-array-rotation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(let [[n k q] (str->nums (read-line))
      dat (to-array (str->nums (read-line)))
      bi (- n k)]
  (doseq [_ (range q)]
    (let [m (Integer/parseInt (read-line))]
      (println (get dat (mod (+ bi m) n))))))
