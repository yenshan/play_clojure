;;
;; https://www.hackerrank.com/challenges/pentagonal-numbers/problem
;;
(ns hackerrank.functional-programming.memoization-and-dp.pentagonal-numbers)

(defn sum-inc-to-n [n]
  (/ (* n (+ n 1)) 2))

(defn P [N]
  (if (= N 1)
    1
    (- (* 5 (sum-inc-to-n (dec N)))
       (* 2 (sum-inc-to-n (- N 2)))
       (- N 2))))

(doseq [_ (range (Integer/parseInt (read-line)))]
  (println (P (Integer/parseInt (read-line)))))

