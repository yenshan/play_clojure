;;
;; https://www.hackerrank.com/challenges/hackerrank-in-a-string/problem
;;
(ns hackerrank.algorithms.string.hackerrank-in-a-string)

(let [q (Integer/parseInt (read-line))]
  (doseq [_ (range q)]
    (let [string (read-line)
          word "hackerrank"]
      (loop [[a & rst :as w] word, [b & rst2] string]
        (cond (nil? a) (println "YES")
              (nil? b) (println "NO")
              (= a b) (recur rst rst2)
              :else (recur w rst2))))))
