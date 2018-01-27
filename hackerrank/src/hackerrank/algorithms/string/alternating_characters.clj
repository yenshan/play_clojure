;;
;; https://www.hackerrank.com/challenges/alternating-characters/problem
;;
(ns hackerrank.algorithms.string.alternating-characters)

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [string (read-line)
          del-res (loop [[a & rst] string, prev-c nil, res []]
                    (cond (nil? a) (conj res prev-c)
                          (nil? prev-c) (recur rst a res)
                          (= a prev-c) (recur rst a res)
                          :else (recur rst a (conj res prev-c))))
          ]
      (println (- (count string) (count del-res)))
      )))

