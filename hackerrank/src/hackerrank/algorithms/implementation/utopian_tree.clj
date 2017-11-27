;;
;; https://www.hackerrank.com/challenges/utopian-tree/problem
;;
(ns hackerrank.algorithms.implementation.utopian-tree
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn tree-height [N]
  (loop [n 1, cyc 0]
    (cond (= cyc N) n
          (even? cyc) (recur (* n 2) (inc cyc))
          :else (recur (inc n) (inc cyc)))))

(defn growth-heights [n]
  (lazy-seq (cons (tree-height n) (growth-heights (inc n)))))

(let [N (Integer/parseInt (read-line))
      input-n (for [_ (range N)]
                (Integer/parseInt (read-line)))
      max-n (apply max input-n)
      tree-h-map (->> (take (inc max-n) (growth-heights 0))
                      to-array)]
  (doseq [e input-n]
    (println (get tree-h-map e))))
