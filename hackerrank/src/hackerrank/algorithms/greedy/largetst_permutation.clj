;;
;;https://www.hackerrank.com/challenges/largest-permutation/problem
;;
(ns hackerrank.algorithms.greedy.largetst-permutation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(defn swap-n [i k m-val-i m-i-val]
  (if (or (>= i (count m-val-i)) (<= k 0))
    m-i-val
    (let [pivot-dat (- (count m-val-i) i)]
      (if (= i (get m-val-i pivot-dat))
        (recur (inc i) k m-val-i m-i-val)
        (let [ri (get m-val-i pivot-dat)
              swap-target-dat (get m-i-val i)] 
          (recur (inc i)
                 (dec k)
                 (-> m-val-i (assoc pivot-dat i) (assoc swap-target-dat ri))
                 (-> m-i-val (assoc i pivot-dat) (assoc ri swap-target-dat))))))))


(defn do-swap-loop [nums k]
  (let [nums-i (map-indexed vector nums)
        m-i-val (into (sorted-map) nums-i)
        m-val-i (reduce (fn [m [i d]]
                          (assoc m d i))
                        {}
                        nums-i)]
    (->> (swap-n 0 k m-val-i m-i-val)
         vec
         (map second))))


(defn print-nums [[x & xs]]
  (if (nil? x)
    (println "")
    (do (print x "") (recur xs))))

(let [[l k] (str->nums (read-line))
      nums (str->nums (read-line))]
  (if (< l k)
    (print-nums (sort #(compare %2 %1) nums))
    (print-nums (do-swap-loop nums k))))
