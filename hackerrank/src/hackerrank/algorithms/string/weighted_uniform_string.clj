;;
;; https://www.hackerrank.com/challenges/weighted-uniform-string/problem
;;
(ns hackerrank.algorithms.string.weighted-uniform-string)

(defn char-weight [c]
  (inc (- (int c) (int \a))))

(defn assoc-when-greater [m c cnt]
  (let [pcnt (get m c)]
    (if (or (nil? pcnt) (< pcnt cnt))
      (assoc m c cnt)
      m)))

(let [string (read-line)
      seq-char-cnt (loop [[a & rst] string, prev-c nil, cnt 0, res {}]
                     (cond (nil? a) (assoc-when-greater res prev-c cnt)
                           (nil? prev-c) (recur rst a 1 res)
                           (= a prev-c) (recur rst a (inc cnt) res)
                           :else (recur rst
                                        a
                                        1
                                        (assoc-when-greater res prev-c cnt))))
      weight-map (reduce (fn [r [c n]]
                           (loop [i 1, ret r]
                             (if (> i n)
                               ret
                               (recur (inc i)
                                      (assoc ret (* i (char-weight c)) 1)))))
                         {}
                         seq-char-cnt)
      ]
  (doseq [_ (range (Integer/parseInt (read-line)))]
    (if (get weight-map (Integer/parseInt (read-line)))
      (println "Yes")
      (println "No")))
  )

