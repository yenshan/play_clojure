;;
;; https://www.hackerrank.com/challenges/leonardo-and-prime/problem
;;
(ns hackerrank.mathmatics.fundamentals.leonardos-prime-factors)


(defn div-factor [n]
  (for [a (range 2 (inc (quot n 2)))
        :when (zero? (mod n a))]
    a))

(defn prime? [n]
  (cond (= n 1) false
        (empty? (div-factor n)) true
        :else false))

(defn prime-seq [p]
  (lazy-seq 
    (if (prime? p)
      (cons p (prime-seq (inc p)))
      (prime-seq (inc p)))))

(defn prime-factors [prd coll]
  (lazy-seq 
    (let [np (* prd (first coll))]
      (cons np (prime-factors np (rest coll))))))

(let [T (Integer/parseInt (read-line))
      nums (for [_ (range T)] (read-string (read-line)))
      maxn (apply max nums)
      pfcoll (->> (prime-factors 1 (prime-seq 2N))
                  (take-while #(<= % maxn)))
      ]
  (doseq [n nums]
    (->> (take-while #(<= % n) pfcoll)
         count
         println)))

      
