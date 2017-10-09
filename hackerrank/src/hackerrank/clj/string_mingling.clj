;;
;;https://www.hackerrank.com/challenges/string-mingling/problem
;;
(ns hackerrank.clj.string-mingling)

(defn merge-string
  ([s1 s2] (merge-string s1 s2 []))
  ([s1 s2 res]
   (if (empty? s1)
     (apply str res)
     (recur (rest s1)
            (rest s2)
            (conj res (first s1) (first s2))))))

(println (merge-string (read-line) (read-line)))
