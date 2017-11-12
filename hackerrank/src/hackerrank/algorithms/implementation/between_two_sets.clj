(ns hackerrank.algorithms.implementation.between-two-sets
  (:require [clojure.string :as s]))

(defn gcd [a b]
  (let [r (mod a b)]
    (if (zero? r)
      b
      (gcd b r))))

(defn scm [big small]
  (loop [tb big, ib 1, 
         ts small, is 2]
    ;(println tb ib ts is)
    (let [nbg (* tb ib)
          nsm (* ts is)]
      (cond (= nbg nsm) nbg
            (> nbg nsm) (recur tb ib ts (inc is))
            :else (recur ts is tb (inc ib))
            ))))

(defn do-ff [f coll]
  (->> (for [a coll, b coll] [a b])
       (filter (fn [[a b]] (not= a b)))
       (reduce (fn [res [a b :as d]]
                 (conj res (sort #(compare %2 %1) d)))
               #{})
       (reduce (fn [res [a b]]
                 (conj res (f a b)))
               #{})
       ))

(defn gcd-coll [coll]
  (->> (do-ff gcd coll)
       sort
       first))

(defn scm-coll [coll]
  (->> (do-ff scm coll)
       (sort #(compare %2 %1))
       first))


(def dat [16 32 96])
(def dat1 [2 4])

(defn integers-between [A B]
  (let [n-scm (scm-coll A)
        n-gcd (gcd-coll B)]
    (->> (iterate #(* % 2) n-scm)
         (take-while #(<= % n-gcd))
         (filter #(= 0 (mod n-gcd %))))))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def _ (read-line))

(def A (str->nums (read-line)))
(def B (str->nums (read-line)))

(->> (integers-between A B)
     count
     println)
