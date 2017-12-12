;;
;; https://www.hackerrank.com/challenges/kaprekar-numbers/problem
;;
(ns hackerrank.algorithms.implementation.modified-kapekar-number)

(defn is-kapekar-num? [n]
  (if (= n 1) 
    true
    (let [strn (str (* n n))
          len (count strn)
          ll (quot len 2)
          rl (+ (quot len 2) (mod len 2))
          lns (subs strn 0 ll)
          rns (subs strn ll len)
          ln (if (= lns "") 0 (Integer/parseInt lns))
          rn (Integer/parseInt rns)
          ]
      (if (zero? rn) 
        false
        (= n (+ ln rn))))))

(let [sn (Integer/parseInt (read-line))
      en (Integer/parseInt (read-line))
      kapekar-num (for [n (range sn (inc en))
                        :when (is-kapekar-num? n)]
                    n)
      ]
  (if (empty? kapekar-num)
    (println "INVALID RANGE")
    (loop [[a & rst] kapekar-num]
      (when a
        (do (print a)
            (when (not (empty? rst))
              (print " "))
            (recur rst))))))

