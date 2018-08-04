(ns prog-math-puzzle.q12
  (:require [clojure.string :as s]))


(defn contain-0to9 [s]
  (= 10 (count (for [c "1234567890"
                     :when (some #(= % c) s)]
                 1))))

(defn get-num [f]
  (loop [n 1]
    (let [s (str (Math/sqrt n))]
      (if (contain-0to9 (f s))
        n
        (recur (inc n))))))

(println (get-num #(take 10 (s/replace % #"\." ""))))
(println (get-num #(take 10 (second (s/split % #"\.")))))

