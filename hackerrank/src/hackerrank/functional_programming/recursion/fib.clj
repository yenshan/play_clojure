;;
;; https://www.hackerrank.com/challenges/functional-programming-warmups-in-recursion---fibonacci-numbers/problem
;;
(ns hackerrank.functional-programming.recursion.fib)

(defn fib [a b]
  (cons a (lazy-seq (fib b (+ a b)))))

(println (nth (fib 0 1) (Integer/parseInt (read-line))))


