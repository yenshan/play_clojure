;;
;; https://www.hackerrank.com/challenges/countingsort1/problem
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
      count-map (reduce (fn [tbl d]
                            (if (get tbl d)
                              (-update tbl d inc)
                              (assoc tbl d 1)))
                          {}
                          arr)]
  (doseq [i (range (+ 1 maxv))]
    (print (if-let [d (get count-map i)]
             d
             0))
    (print (if (< i maxv) " " "\n"))))

