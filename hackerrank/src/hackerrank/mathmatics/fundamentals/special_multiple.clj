;;
;; https://www.hackerrank.com/challenges/special-multiple/problem
;;
(ns hackerrank.mathmatics.fundamentals.special-multiple)


(defn comb-9-0 [n res]
  (if (zero? n)
    res
    (recur (dec n)
           (for [a res
                 b [0 9]]
             (cons b a)))))

(defn to-i [coll]
  (loop [[x & xs] coll
         base 1
         res 0]
    (if (nil? x)
      res
      (recur xs
             (* base 10)
             (+ res (* base x))))))

(defn find-min-divisible [n]
  (let [eo (if (even? n) even? odd?)]
    (loop [i 0]
      (let [xs (comb-9-0 i [[9]])
            res (->> (map to-i xs)
                     (filter #(zero? (mod % n))))
            ]
        (if (empty? res)
          (recur (inc i))
          (first res))))))

(let [T (Integer/parseInt (read-line))]
  (loop [cnt T, m {}]
    (when (> cnt 0)
      (let [n (Integer/parseInt (read-line))
            res (or (get m n) (find-min-divisible n))
            ]
        (println res)
        (recur (dec cnt) (assoc m n res))))))

