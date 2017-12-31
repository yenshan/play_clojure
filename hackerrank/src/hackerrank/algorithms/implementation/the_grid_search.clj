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
          [r c] (str->nums (read-line))
          index (fn [r c] (+ c (* r C)))
          pattern (->> (read-matrix r)
                       (apply concat)
                       (map-indexed vector)
                       (map (fn [[i d]]
                              [(index (quot i c) (mod i c))
                               d]))
                       )
          first-c (second (first pattern))
          matrix-len (count matrix)
          ptn-is-in (loop [i 0]
                      (if (>= i matrix-len)
                        nil
                        (if (and (= first-c (get matrix i))
                                 (every? #(and (< (+ i (first %)) matrix-len)
                                               (= (get matrix (+ i (first %))) (second %)))
                                         pattern))
                          true
                          (recur (inc i)))))
          ]
      (if ptn-is-in
        (println "YES")
        (println "NO"))
     )))
          

