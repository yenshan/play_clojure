;;
;; https://www.hackerrank.com/challenges/organizing-containers-of-balls/problem
;;
(ns hackerrank.algorithms.implementation.organizing-ontainers-of-balls
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [q (Integer/parseInt (read-line))]
  (doseq [_ (range q)]
    (let [n (Integer/parseInt (read-line))]
      (letfn [(is-col? [c i] (= c (mod i n)))
              (is-row? [r i] (= r (quot i n)))
              (sum-coll [f coll]
                (for [i (range n)] 
                  (reduce #(+ %1 (second %2)) 0
                          (filter #(f i (first %)) coll))))
              ]
        (let [dats (map-indexed vector
                            (apply concat 
                                   (for [_ (range n)]
                                     (str->nums (read-line)))))
              col-seq (sort (sum-coll is-col? dats))
              row-seq (sort (sum-coll is-row? dats))
              ]
          (if (= col-seq row-seq)
            (println "Possible")
            (println "Impossible"))
      )))))

