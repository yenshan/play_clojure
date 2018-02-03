;;
;; https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem
;;
(ns hackerrank.algorithms.string.sherlock-and-the-valid-string)

(defn cnt-map [coll]
  (->> coll
       (reduce (fn [res c]
                 (if (get res c)
                   (update res c inc)
                   (assoc res c 1)))
               {})))

(let [string (read-line)
      cnt-char (map second (cnt-map string))
      cnt-freq (cnt-map cnt-char)
      ]
  (cond (= 1 (count cnt-freq)) (println "YES")
        (< 2 (count cnt-freq)) (println "NO")
        :else (let [o (filter (fn [d] (when (= 1 (second d)) d)) cnt-freq)]
                (if (empty? o)
                  (println "NO")
                  (if (= 1 (first (first o)))
                    (println "YES")
                    (if (= 1 (Math/abs (- (first (first cnt-freq))
                                          (first (second cnt-freq)))))
                      (println "YES")
                      (println "NO")))))))

