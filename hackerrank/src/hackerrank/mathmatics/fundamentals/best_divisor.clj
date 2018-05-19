;;
;; https://www.hackerrank.com/challenges/best-divisor/problem
;;
(ns hackerrank.mathmatics.fundamentals.best-divisor)


(defn divisors [n]
  (->> (range 1 (+ n 1))
       (filter #(zero? (mod n %)))))

(defn to-digit-seq [n]
  (loop [dn n
         res []]
    (if (zero? dn)
      res
      (recur (quot dn 10)
             (cons (mod dn 10) res)))))

(defn digit-sum [n]
  (->> (to-digit-seq n)
       (reduce +)))

(let [n (Integer/parseInt (read-line))]
  (->> (divisors n)
       (sort (fn [a b]
               (let [dsa (digit-sum a)
                     dsb (digit-sum b)]
                 (if (= dsa dsb)
                   (compare a b)
                   (compare dsb dsa)))))
       first
       println))

