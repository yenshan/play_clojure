;;
;; https://www.hackerrank.com/challenges/functional-programming-warmups-in-recursion---gcd/problem
;;
(ns hackerrank.functional-programming.recursion.gcd)

(let [f (fn [a b] 
          (cond (= a b) a
                (> a b) (recur (- a b) b)
                :else (recur (- b a) a)))
      [m n] (map read-string (re-seq #"\d+" (read-line)))]
  (println (f m  n)))
