;;
;; https://www.hackerrank.com/challenges/sherlock-and-divisors/problem
;;
(ns hackerrank.mathmatics.fundamentals.sherlock-and-divisors)

;;
;; Good problem. It is not easy to find good algorithm in performance.
;; I read forum discussions.
;;

(defn divisors [n]
  (->> (range 1 (+ 1 (Math/sqrt n)))
       (reduce (fn [res d]
                 (if (zero? (mod n d))
                   (conj res d (quot n d))
                   res))
               #{})
       (filter #(zero? (mod % 2)))))

(let [t (Integer/parseInt (read-line))]
  (doseq [_ (range t)]
    (let [n (Integer/parseInt (read-line))]
      (println (if (odd? n)
                 0
                 (count (divisors n)))))))

