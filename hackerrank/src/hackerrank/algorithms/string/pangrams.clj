;;
;; https://www.hackerrank.com/challenges/pangrams/problem
;;
(ns hackerrank.algorithms.string.pangrams)

(let [string (read-line)
      char-map (reduce (fn [res c]
                         (assoc res (Character/toLowerCase c) 1))
                       {}
                       (filter #(not= % \space) string))]
  (println (if (= 26 (count char-map))
             "pangram"
             "not pangram")))

