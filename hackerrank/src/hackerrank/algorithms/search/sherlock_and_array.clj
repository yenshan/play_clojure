;;
;; https://www.hackerrank.com/challenges/sherlock-and-array/problem
;;
(ns hackerrank.algorithms.search.sherlock-and-array
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn sum-seq [array indexs]
  (second 
    (reduce (fn [[sum res] i]
              (let [d (get array i)]
                [(+ sum d)
                 (assoc res i (+ sum d))]))
            [0 {}]
            indexs)))

(defn some-same? [map1 map2]
  (some (fn [d]
          (when-let [e (get map2 (first d))]
            (= e (second d))))
        (vec map1)))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [_ (read-line)
          nums (to-array (str->nums (read-line)))
          len (count nums)
          sum-from-left (sum-seq nums (range len))
          sum-from-right (sum-seq nums (range (dec len) -1 -1))
          res (some-same? sum-from-left sum-from-right)
          ]
      (if res
        (println "YES")
        (println "NO")))))

