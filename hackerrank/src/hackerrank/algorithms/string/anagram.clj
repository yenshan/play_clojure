;;
;; https://www.hackerrank.com/challenges/anagram/problem
;;
(ns hackerrank.algorithms.string.anagram
  (:require [clojure.string :as s]))


(defn remove-all-in-coll [coll1 coll2]
  (loop [[a & rst] coll2, res coll1]
    (if (nil? a)
      res
      (let [c (re-pattern (str a))]
        (recur rst (s/replace-first res c ""))))))

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [string (read-line)
          hlen (quot (count string) 2)
          pre-half (subs string 0 hlen)
          rear-half (subs string hlen)]
      (if (not= (count pre-half) (count rear-half))
        (println -1)
        (->> (remove-all-in-coll pre-half rear-half)
             count
             println)))))


