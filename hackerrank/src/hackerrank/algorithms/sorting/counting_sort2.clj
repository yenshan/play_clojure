;;
;; https://www.hackerrank.com/challenges/countingsort2/problem
;;
(ns hackerrank.algorithms.sorting.counting-sort1
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn -update [m d f]
  (let [v (get m d)]
    (assoc m d (f v))))

(let [_ (read-line)
      arr (str->nums (read-line))
      maxv (apply max arr)
      zero-arry (reduce (fn [m d] (assoc m d 0)) {}
                        (take (+ 1 maxv) (iterate inc 0)))
      cnt-map (reduce (fn [m d] (-update m d inc)) zero-arry arr)
      sort-arry (apply concat (for [i (range (+ 1 maxv))]
                                (repeat (get cnt-map i) i)))
      ]
  (doseq [e (interpose " " sort-arry)]
    (print e))
  (println ""))
