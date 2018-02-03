;;
;; :https://www.hackerrank.com/challenges/string-construction/problem
;;
(ns hackerrank.algorithms.string.string-construction
  (:require [clojure.string :as s]))

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [string (read-line)
          cnt (loop [[a & rst] string, char-map {}, cnt 0]
                (if (nil? a)
                  cnt
                  (if (get char-map a)
                    (recur rst char-map cnt)
                    (recur rst
                           (assoc char-map a 1)
                           (inc cnt)))))
          ]
      (println cnt))))

