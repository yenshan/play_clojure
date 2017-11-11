;;
;; https://www.hackerrank.com/challenges/handshake/problem
;;
(ns hackerrank.mathmatics.fundamentals.handshake)

(defn cnt-shake [n]
  (condp = n
    1 0
    2 1
    (+ (dec n) (cnt-shake (dec n)))))

(doseq [_ (range (Integer/parseInt (read-line)))]
 (println (cnt-shake (Integer/parseInt (read-line))))
 )
