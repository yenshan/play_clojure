(ns hackerrank.algorithms.implementation.the-grid-search
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn read-matrix [r]
  (loop [n r, res []]
    (if (<= n 0)
      res 
      (recur (dec n) 
             (conj res (read-line))))))

(let [T (Integer/parseInt (read-line))]
  (doseq [i (range T)]
    (let [[R C] (str->nums (read-line))
          matrix (->> (read-matrix R)
                      (apply str)
                      to-array
                      )
          matrix-len (count matrix)
          [r c] (str->nums (read-line))
          index (fn [r c] (+ c (* r C)))
          pattern (to-array (apply concat (read-matrix r)))
          ptn-len (count pattern)
          ptn-idx (to-array (for [ir (range r) ic (range c)] (index ir ic)))
          first-c (get pattern 0)
          ptn-is-in (loop [i 0]
                      (if (<= matrix-len (+ i ptn-len))
                        nil
                        (if (or (< C (+ (mod i C) c))
                                (not= first-c (get matrix i)))
                          (recur (inc i))
                          (if (every? #(= (get matrix (+ i (get ptn-idx %)))
                                          (get pattern %))
                                      (range ptn-len))
                              true
                              (recur (inc i))))))
          ]
      (if ptn-is-in
        (println "YES")
        (println "NO"))
     )))
          

