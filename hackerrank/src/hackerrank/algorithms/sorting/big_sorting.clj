;;
;; https://www.hackerrank.com/challenges/big-sorting/problem
;;
(ns hackerrank.algorithms.sorting.big-sorting)

(let [n (Integer/parseInt (read-line))
      nums (for [_ (range n)]
             (read-line))
      sort-result (sort (fn [a b]
                          (if (= (count a) (count b))
                            (compare a b)
                            (compare (count a) (count b))))
                        nums)
      ]
  (doseq [n sort-result]
    (println (str n)))
  )

