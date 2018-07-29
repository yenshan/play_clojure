(ns hackerrank.interview-preparation-kit.arrays.array-manipulation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn to-index-map [coll]
  (->> (map (fn [a b] [a b]) (iterate inc 1) coll)
       (reduce (fn [m [i d]]
                 (assoc m i d))
               {}
               )))

(defn -update [m k f]
  (let [d (get m k)]
    (assoc m k (f d))))

(defn do-operation [idxm [si ei d]]
  (reduce (fn [m i]
            (-update m i #(+ d %)))
          idxm
          (range si (inc ei))))

(let [[n m] (str->nums (read-line))
      arrm (to-index-map (take n (repeat 0)))
      ops (for [_ (range m)]
            (str->nums (read-line)))
      res (reduce #(do-operation %1 %2) arrm ops)
      ]
  (println (apply max (vals res))))

