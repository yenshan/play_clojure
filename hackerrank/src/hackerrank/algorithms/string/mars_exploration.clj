;;
;; https://www.hackerrank.com/challenges/mars-exploration/problem
;;
(ns hackerrank.algorithms.string.mars-exploration)

(defn cnt-diff [coll1 coll2]
  (loop [[a & rst1] coll1, [b & rst2] coll2, cnt 0]
    (if (nil? a)
      cnt
      (if (not= a b)
        (recur rst1 rst2 (inc cnt))
        (recur rst1 rst2 cnt)))))

(let [string (read-line)
      comp-str (->> (repeat "SOS")
                    (take (/ (count string) 3))
                    (apply str))]
  (println (cnt-diff comp-str string)))

