;;
;; https://www.hackerrank.com/challenges/acm-icpc-team/problem
;;
(ns hackerrank.algorithms.implementation.acm-icpc-team
  (:require [clojure.string :as s]))

;;
;; GOOD problem.
;; You should use bit operation and manage big number, and this is not so difficult in Clojure.
;; You can imporove performance 32 times than which does not use bit operation.
;; (Clojure's long is 32 wide integer)
;;

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))
(defn subs-32 [string]
  (let [str-len (count string)]
    (loop [i 0, res []]
      (if (>= i str-len)
        res
        (let [end (if (< str-len (+ i 31))
                    str-len
                    (+ i 31))]
          (recur end
                 (conj res (subs string i end))))))))

(defn bit-or-coll [col1 col2]
  (map (fn [^long a ^long b] 
         (bit-or a b))
       col1 col2))

(defn count-bits [number]
  (loop [^long n number, cnt 0]
    (if (< n 2)
      (+ cnt n)
      (recur (quot n 2)
             (+ cnt (mod n 2))))))

(defn count-bits-coll [coll]
  (reduce (fn [^long res d]
            (+ res (count-bits d)))
          0
          coll))


(defn all-pairs [coll]
  (loop [[a & rst] coll, res []]
    (if (empty? rst)
      res
      (recur rst
             (reduce (fn [r e]
                       (conj r [a e]))
                     res rst)))))

(let [[n m] (str->nums (read-line))
      topics (for [_ (range n)]
               (map #(Integer/parseInt % 2)
                    (subs-32 (read-line))))
      pairs (all-pairs topics)
      [max-n cnt-pairs] (reduce (fn [[maxn col :as d] [a b]] 
                                  (let [cnt (count-bits-coll (bit-or-coll a b))]
                                    (if (>= cnt maxn) 
                                      [cnt (conj col cnt)]
                                      d)))
                                [0 []]
                                pairs)
      cnt-max (count (filter #(= % max-n) cnt-pairs))
      ]
  (println max-n)
  (println cnt-max)
  )
