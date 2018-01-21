;;
;; https://www.hackerrank.com/challenges/mars-exploration/problem
;;
(ns hackerrank.algorithms.string.mars-exploration2)

(->> (read-line)
     (partition 3)
     (reduce (fn [cnt [c1 c2 c3]]
               (+ cnt
                  (if (not= c1 \S) 1 0)
                  (if (not= c2 \O) 1 0)
                  (if (not= c3 \S) 1 0)))
             0)
     println)

