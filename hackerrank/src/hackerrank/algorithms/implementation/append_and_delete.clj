;;
;; https://www.hackerrank.com/challenges/append-and-delete/problem
;;
(ns hackerrank.algorithms.implementation.append-and-delete
  (:require [clojure.string :as s]))


(defn count-same-chars-from-head [str1 str2]
  (loop [[a & rst1] str1, [b & rst2] str2, cnt 0]
    (cond (or (nil? a) (nil? b)) cnt
          (not= a b) cnt
          :else (recur rst1 rst2 (inc cnt)))))

(defn count-diff-chars [str1 str2]
  (let [n-same (count-same-chars-from-head str1 str2)]
    (+ (- (count str1) n-same)
       (- (count str2) n-same))))

(let [str1 (read-line)
      str2 (read-line)
      k (Integer/parseInt (read-line))]
  (if (>= k (+ (count str1) (count str2)))
    (println "Yes")
    (let [dc (count-diff-chars str1 str2)
          dn (- k dc)]
      (if (and (>= dn 0) (zero? (mod dn 2)))
        (println "Yes")
        (println "No")))))
        
