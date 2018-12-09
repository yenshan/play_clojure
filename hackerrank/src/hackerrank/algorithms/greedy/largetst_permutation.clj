(ns hackerrank.algorithms.greedy.largetst-permutation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(defn maxv [m-i-val from]
  (loop [i (inc from)
         mx (get m-i-val from)]
    (if (>= i (count m-i-val))
      mx
      (let [d (get m-i-val i)]
        (if (> d mx)
          (recur (inc i) d)
          (recur (inc i) mx))))))

(defn swap-n [i n m-val-i m-i-val]
  (if (<= n 0)
    m-val-i
    (let [maxval (maxv m-i-val i)
          maxvi (get m-val-i maxval)
          ival (get m-i-val i)]
      (recur (inc i)
             (dec n)
             (-> m-val-i (assoc maxval i) (assoc ival maxvi))
             (-> m-i-val (assoc i maxval) (assoc maxvi ival))))))

(defn do-swap-loop [nums k]
  (let [nums-i (map-indexed vector nums)
        m-i-val (reduce (fn [m [i d]]
                          (assoc m i d))
                        {}
                        nums-i)
        m-val-i (reduce (fn [m [i d]]
                          (assoc m d i))
                        {}
                        nums-i)]
    (map first (sort-by second (vec (swap-n 0 k m-val-i m-i-val))))))


(defn print-nums [[x & xs]]
  (if (nil? x)
    (println "")
    (do (print x "") (recur xs))))

(let [[l k] (str->nums (read-line))
      nums (str->nums (read-line))]
  (if (< l k)
    (print-nums (sort #(compare %2 %1) nums))
    (print-nums (do-swap-loop nums k))))
