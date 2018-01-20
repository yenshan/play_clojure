;;
;; https://www.hackerrank.com/challenges/mars-exploration/problem
;;
(ns hackerrank.algorithms.string.mars-exploration)

(let [string (read-line)]
  (->> (repeat "SOS")
       (take (/ (count string) 3))
       (apply str)
       (filter #(not= %1 %2) string)
       count
       println))

