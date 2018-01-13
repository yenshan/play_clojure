;;
;; https://www.hackerrank.com/challenges/camelcase/problem
;;
(ns hackerrank.algorithms.string.camelcase
  (:require [clojure.string :as s]))

(defn is-upper-case? [c]
  (and (<= 0 (compare c \A))
       (>= 0 (compare c \Z))))


(->> (read-line)
     (filter is-upper-case?)
     count
     inc
     println)
      

