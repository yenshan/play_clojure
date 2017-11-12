;;
;; https://www.hackerrank.com/challenges/between-two-sets/problem
;;
(ns hackerrank.algorithms.implementation.between-two-sets
  (:require [clojure.string :as s]))

(defn gcd [a b]
  (let [r (mod a b)]
    (if (zero? r)
      b
      (recur b r))))

(defn scm [big small]
  (loop [tb big, ib 1, 
         ts small, is 2]
    (let [nbg (* tb ib)
          nsm (* ts is)]
      (cond (= nbg nsm) nbg
            (> nbg nsm) (recur tb ib ts (inc is))
            :else (recur ts is tb (inc ib))
            ))))

(defn do-ff [f coll]
  (if (= 1 (count coll))
    coll
    (->> (for [a coll, b coll] [a b])
         (filter (fn [[a b]] (not= a b)))
         (reduce (fn [res d]
                   (conj res (sort #(compare %2 %1) d)))
                 #{})
         (reduce (fn [res [a b]]
                   (conj res (f a b)))
                 #{})
         )))

(defn gcd-coll [coll]
  (let [res (->> (do-ff gcd coll)
                 sort
                 first)]
    (if (some #(not= 0 (mod % res)) coll)
      (recur (conj coll res))
      res)))

(defn scm-coll [coll]
  (let [res (->> (do-ff scm coll)
                 (sort #(compare %2 %1))
                 first)]
    (if (some #(not= 0 (mod res %)) coll)
      (recur (conj coll res))
      res)))

(defn integers-between [A B]
  (if (> (last A) (first B))
    '()
    (let [n-scm (scm-coll A)
          n-gcd (gcd-coll B)]
      (->> (map #(* % n-scm) (iterate inc 1))
           (take-while #(<= % n-gcd))
           (filter #(= 0 (mod n-gcd %)))
           ))))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def _ (read-line))

(def A (sort (str->nums (read-line))))
(def B (sort (str->nums (read-line))))

(->> (integers-between A B)
     count
     println)
