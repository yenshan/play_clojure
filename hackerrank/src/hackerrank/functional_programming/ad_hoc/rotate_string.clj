;;
;; https://www.hackerrank.com/challenges/rotate-string/problem
;;
(ns hackerrank.functional-programming.ad-hoc.rotate-string
  (:require [clojure.string :as s]))

(defn rotate-once [[a & rst]]
    (apply str (conj (vec rst) a)))

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [string (read-line)
          res (loop [s string, cnt 1, res ""]
                (let [ss (rotate-once s)]
                  (if (>= cnt (count string))
                    (str res " " ss)
                    (recur ss (inc cnt) (str res " " ss)))))
          ]
      (println (s/trim res)))))
