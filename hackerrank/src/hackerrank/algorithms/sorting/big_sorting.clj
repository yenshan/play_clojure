;;
;; https://www.hackerrank.com/challenges/big-sorting/problem
;;
(ns hackerrank.algorithms.sorting.big-sorting)

(let [n (Integer/parseInt (read-line))
      result (->> (for [_ (range n)]
                    (read-line))
                  (sort (fn [a b]
                          (if (= (count a) (count b))
                            (compare a b)
                            (compare (count a) (count b))))))
      ]
  (doseq [n result]
    (println n)))

