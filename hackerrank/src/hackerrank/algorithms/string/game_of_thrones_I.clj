;;
;; https://www.hackerrank.com/challenges/game-of-thrones/problem
;;
(ns hackerrank.algorithms.string.game-of-thrones-I)

(let [string (read-line)
      char-cnt-map (reduce (fn [res c]
                             (if (get res c)
                               (update res c inc)
                               (assoc res c 1)))
                           {}
                           string)
      cnt-odd (count (filter (fn [[_ n]] (odd? n)) char-cnt-map))
      ]
  (if (<= cnt-odd 1)
    (println "YES")
    (println "NO"))
  )

