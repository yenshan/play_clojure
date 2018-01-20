;;
;; https://www.hackerrank.com/challenges/mars-exploration/problem
;;
(ns hackerrank.algorithms.string.mars-exploration)

(let [string (read-line)]
  (->> (repeat "SOS")
       (take (/ (count string) 3))
       (apply str)
       (map #(not= %1 %2) string)
       (filter true?)
       count
       println))

