;;
;;https://www.hackerrank.com/challenges/drawing-book/problem
;;
(ns hackerrank.algorithms.implementation.drawing-book
  (:require [clojure.string :as s]))

(def total-pages (Integer/parseInt (read-line)))

(def n (Integer/parseInt (read-line)))

(let [block-of-n (quot n 2)
      block-of-last  (quot total-pages 2)
      distance-from-last (- block-of-last block-of-n)]
  (if (< block-of-n distance-from-last)
    (println block-of-n)
    (println distance-from-last)))
