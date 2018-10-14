;;
;;https://www.hackerrank.com/challenges/beautiful-pairs/problem
;;
(ns hackerrank.algorithms.greedy.beautiful-pairs
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [N (Integer/parseInt (read-line))
      A (str->nums (read-line))
      B (str->nums (read-line))
      Ai (map-indexed vector A)
      Bi (map-indexed vector B)
      bpairs (for [a Ai b Bi
                   :when (= (second a) (second b))]
               [(first a) (first b)])
      unq-pairs (loop [[x & xs] bpairs
                       res []]
                  (if (nil? x)
                    res
                    (recur (->> xs
                                (remove #(= (second x) (second %)))
                                (remove #(= (first x) (first %)))
                                )
                           (conj res x))))
      cnt-pairs (count unq-pairs)
      diff (if (= cnt-pairs N) -1 1)
      ]
  (println (+ cnt-pairs diff)))
 
