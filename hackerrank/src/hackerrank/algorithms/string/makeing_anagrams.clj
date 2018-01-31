;;
;; https://www.hackerrank.com/challenges/making-anagrams/problem
;;
(ns hackerrank.algorithms.string.makeing-anagrams
  (:require [clojure.string :as s]))


(defn del-chars-in [src-str dst-str]
  (loop [[a & rst] src-str, res dst-str]
    (if (nil? a)
      res
      (let [c (re-pattern (str a))]
        (recur rst (s/replace-first res c ""))))))

(let [str1 (read-line)
      str2 (read-line)
      del-chars1 (del-chars-in str2 str1)
      rst-str1 (del-chars-in del-chars1 str1)
      del-chars2 (del-chars-in str1 str2)
      ]
  (println (+ (count del-chars1) (count del-chars2)))
  )
