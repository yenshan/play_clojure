;;
;; https://www.hackerrank.com/challenges/sherlock-and-squares/problem
;;
(ns hackerrank.algorithms.implementation.sherlock-and-squares
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn square [^long x] (* x x))

(defn square-nums-until [maxn]
  (loop [n 1, res [], cnt 0]
    (let [sq-n (square n)]
      (if (> sq-n maxn)
        res
        (recur (inc n) 
               (conj res sq-n)
               (inc cnt))))))

(defn get-max-sqr-indx [n dat]
  (loop [i (dec (count dat))]
    (cond (< i 0) nil
          (>= n (get dat i)) i
          :else (recur (dec i)))))

(defn get-min-sqr-indx [n dat]
  (loop [i 0]
    (cond (>= i (count dat)) nil
          (<= n (get dat i)) i
          :else (recur (inc i)))))

(let [T (Integer/parseInt (read-line))
      dat (for [_ (range T)]
            (str->nums (read-line)))
      max-n (apply max (map second dat))
      sqr-array (to-array (square-nums-until max-n))]
  (doseq [[minn maxn] dat]
    (let [mini (get-min-sqr-indx minn sqr-array)
          maxi (get-max-sqr-indx maxn sqr-array)]
      (if (or (nil? maxi) (nil? mini))
        (println 0)
        (println (inc (- maxi mini)))))))
