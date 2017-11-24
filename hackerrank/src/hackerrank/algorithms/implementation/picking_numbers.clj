;;
;; https://www.hackerrank.com/challenges/picking-numbers/problem
;;
(ns hackerrank.algorithms.implementation.picking-numbers2
  (:require [clojure.string :as s]))

;;
;; Solved!! 
;; This is GOOD problem!
;; I spent a lot of thiking time to solve this problem. 
;; Ultimately the answer is very simple.
;;

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn count-chosen-integer [n coll]
  (count (filter (fn [d]
                   (>= 1 (Math/abs (- d n))))
                 coll)))

(defn num-of-chosen-integer [coll]
  (loop [dat (sort coll)]
    (if (< (count dat) 2)
      (count dat)
      (let [first-d (first dat)
            last-d (last dat)
            dlf (Math/abs (- first-d last-d))
            ]
        (if (<= dlf 1)
          (count dat)
          (let [cnt-first-ci (count-chosen-integer first-d (rest dat))
                cnt-last-ci (count-chosen-integer last-d (drop-last dat))
                ]
            (recur (if (< cnt-first-ci cnt-last-ci) 
                     (remove #(= % first-d) dat) 
                     (remove #(= % last-d) dat)))))))))

(def _ (read-line))

(->> (read-line)
     str->nums
     num-of-chosen-integer
     println)

