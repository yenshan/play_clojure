;;
;; https://www.hackerrank.com/challenges/fibonacci-fp/problem
;;
(ns hackerrank.functional-programming.memoization-and-dp.fibonacci
  (:require [clojure.string :as s]))

(defn fib-seq [a b]
  (lazy-seq (cons a (fib-seq b (+ a b)))))

(defn fib-map [n]
  (->> (take (inc n) (fib-seq 0N 1N))
       (map-indexed vector)
       (reduce (fn [res [i d]]
                 (assoc res i d))
               {})))

(def modn (+ (* 10N 10 10 10 10 10 10 10) 7))

(def N (Integer/parseInt (read-line)))

(def req-coll (for [_ (range N)]
                (Integer/parseInt (read-line))))

(let [fm (fib-map (apply max req-coll))]
  (doseq [n req-coll]
    (println (str (mod (get fm n) modn)))))


