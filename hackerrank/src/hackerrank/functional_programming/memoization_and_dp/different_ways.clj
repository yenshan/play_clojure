(ns hackerrank.functional-programming.memoization-and-dp.different-ways
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn count-lemur [n k]
  (cond (zero? k) 1
        (= k n) 1
        :else (+ (count-lemur (dec n) (dec k))
                 (count-lemur (dec n) k))))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [[N K] (str->nums (read-line))]
    (println (count-lemur N K)))))

