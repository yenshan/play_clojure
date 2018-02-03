;;
;; :https://www.hackerrank.com/challenges/string-construction/problem
;;
(ns hackerrank.algorithms.string.string-construction)

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (->> (read-line)
         (reduce (fn [[cnt char-map] c]
                   (if (get char-map c)
                     [cnt char-map]
                     [(inc cnt) (assoc char-map c 1)]))
                 [0 {}])
         first
         println)))

