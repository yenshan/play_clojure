;;
;; https://www.hackerrank.com/challenges/strong-password/problem
;;
(ns hackerrank.algorithms.string.strong-password
  (:require [clojure.string :as s]))


(defn special-char? [c]
  (some #(= % c) "!@#$%^&*()-+"))

(defn count-if [f coll]
  (count (filter f coll)))

(defn num-need-add [your-num target-num]
  (if (< your-num target-num)
    (- target-num your-num)
    0))

(let [_ (read-line)
      inputs (read-line)
      num-of-upper (count-if #(Character/isUpperCase %) inputs)
      num-of-lower (count-if #(Character/isLowerCase %) inputs)
      num-of-digit (count-if #(Character/isDigit %) inputs)
      num-of-spc (count-if special-char? inputs)
      ]
  (println (max (num-need-add (count inputs) 6)
                (+ (num-need-add num-of-upper 1)
                   (num-need-add num-of-lower 1)
                   (num-need-add num-of-digit 1)
                   (num-need-add num-of-spc 1)))))
      
