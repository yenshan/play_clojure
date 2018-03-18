(ns prog-math-puzzle.q01
  (:require [clojure.string :as s]))

(defn to-numlist [number base]
  (loop [n number
         res []]
    (if (zero? n)
      res
      (recur (quot n base)
             (cons (mod n base) res)))))

(defn palindrome? [n base]
  (let [nl (to-numlist n base)]
    (= nl (reverse nl))))

(defn palin-10-8-2? [n]
  (and (palindrome? n 10)
       (palindrome? n 8)
       (palindrome? n 2)))

(defn palindrome-seq [from]
  (->> (iterate #(+ % 2) from)
       (filter palin-10-8-2?)))
  
(println (take 1 (palindrome-seq 11)))
