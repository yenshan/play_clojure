;;
;; https://www.hackerrank.com/challenges/gem-stones/problem
;;
(ns hackerrank.algorithms.string.gemstones)

(let [N (Integer/parseInt (read-line))
      rocks (for [_ (range N)] 
              (set (read-line)))
      cnt-gems (->> (map vec rocks)
                    (apply concat)
                    (reduce (fn [res d]
                              (if (get res d)
                                (update res d inc)
                                (assoc res d 1)))
                            {}))
      common-gems (filter (fn [[_ n]] (= n N)) cnt-gems)
      ]
  (println (count common-gems))
  )


