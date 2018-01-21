;;
;; https://www.hackerrank.com/challenges/mars-exploration/problem
;;
(ns hackerrank.algorithms.string.mars-exploration3)

(let [string (read-line)]
  (println 
    (loop [i 0, cnt 0]
      (if (>= i (count string))
        cnt
        (recur (+ i 3)
               (+ cnt
                  (if (not= \S (.charAt string i)) 1 0)
                  (if (not= \O (.charAt string (+ i 1))) 1 0)
                  (if (not= \S (.charAt string (+ i 2))) 1 0)))))))

