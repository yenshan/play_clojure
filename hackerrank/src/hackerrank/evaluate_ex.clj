(ns hackerrank.evaluate-ex
  (:require [clojure.test :refer :all]))

;;
;; https://www.hackerrank.com/challenges/eval-ex/problem
;;
;; The series expansion of is given by:
;; 1 + x + x^2/2! + x^3/3! + x^4/4! ...
;; Evaluate for given values of by using the above expansion for the first 10 terms. 

(defn expt [n]
  (if (= n 1)
    1
    (* n (expt (dec n)))))

(defn pow [b n]
  (if (zero? n)
    1
    (* b (pow b (dec n)))))

(defn e-power-x [x]
  (reduce #(+ %1 
              (/ (pow x %2) (expt %2)))
          1.0
          (range 1 10)))

(deftest a-test
  (testing "e-power-x"
    (is (= "2423600.1887" (format "%.4f" (e-power-x 20.0000))))
    (is (= "143.6895" (format "%.4f" (e-power-x 5.0000))))
    (is (= "1.6487" (format "%.4f" (e-power-x 0.5000))))
    (is (= "0.6065" (format "%.4f" (e-power-x -0.5000))))
    ))


