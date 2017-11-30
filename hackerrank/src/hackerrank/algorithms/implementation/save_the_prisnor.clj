;;
;; https://www.hackerrank.com/challenges/save-the-prisoner/problem
;;
(ns hackerrank.algorithms.implementation.save-the-prisnor
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def T (Integer/parseInt (read-line)))

(doseq [_ (range T)]
  (let [[n m s] (str->nums (read-line))
         base (mod m n)
         base2 (if (zero? base) n base)
         pos (mod (+ s (dec base2)) n)]
    (println (if (zero? pos) n pos))))

