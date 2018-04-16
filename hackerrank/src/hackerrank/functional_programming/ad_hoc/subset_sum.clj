;;
;; https://www.hackerrank.com/challenges/subset-sum/problem
;;
(ns hackerrank.functional-programming.ad-hoc.subset-sum
  (:require [clojure.string :as s]))

;;
;; good problem.
;;

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn accumlate-sum [coll]
  (loop [[x & xs] (rest coll)
         prev (first coll)
         res []]
    (if (nil? x)
      (conj res prev)
      (recur xs 
             (+ prev x) 
             (conj res prev)))))

(defn find-pos [d l r xs]
  (cond (<= d (get xs l)) (+ l 1)
        (> d (get xs r)) -1
        :else (let [mp (+ l (quot (- r l) 2))]
                (cond (< d (get xs mp)) (recur d (+ l 1) mp xs)
                      (> d (get xs mp)) (recur d (+ mp 1) r xs)
                      :else (+ mp 1)))))

(defn run []
  (let [_ (read-line)
        nums (sort #(compare %2  %1) (str->nums (read-line)))
        len (count nums)
        nums2 (accumlate-sum nums)
        num-array (to-array nums2)
        T (read-string (read-line))
        ]
    (doseq [_ (range T)]
      (let [S (read-string (read-line))
            res (find-pos S 0 (- len 1) num-array)
            ]
        (println res)))))

(run)
