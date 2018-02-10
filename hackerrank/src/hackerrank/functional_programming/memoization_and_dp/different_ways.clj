;;
;; https://www.hackerrank.com/challenges/different-ways-fp/problem
;;
(ns hackerrank.functional-programming.memoization-and-dp.different-ways
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def mod-base (+ 7 (apply * (take 8 (repeat 10)))))

(defn factorial [number]
  (loop [n number, res 1N]
    (if (zero? n)
      res
      (recur (dec n) (* n res)))))

(defn count-lemur [n k]
  (/ (factorial n)
     (* (factorial k)
        (factorial (- n k)))))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [[N K] (str->nums (read-line))]
    (println (str (mod (count-lemur N K)
                       mod-base))))))

