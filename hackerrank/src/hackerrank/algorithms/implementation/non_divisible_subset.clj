;;
;; https://www.hackerrank.com/challenges/non-divisible-subset/problem
;;
(ns hackerrank.algorithms.implementation.non-divisible-subset
  (:require [clojure.string :as s]))
 

;;
;; GOOD problem.
;; You need to think different way of idea to solve this problem in computable time.
;; => see discussion board.
;;

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(let [[n k] (str->nums (read-line))
      dat (str->nums (read-line))
      rem-lst (map #(mod % k) dat)
      cnt-rem (sort-by second #(compare %2 %1)
                       (vec 
                         (reduce (fn [res d]
                                   (if (res d)
                                     (update res d inc)
                                     (assoc res d 1)))
                                 {} rem-lst)))
     rm-cnt (loop [[a & rst :as lst] cnt-rem, res 0]
              (if (empty? lst)
                res
                (let [rn (first a)
                      cnt (second a)]
                  (cond (and (zero? rn) (< 1 cnt)) (recur rst (+ res (dec cnt)))
                        (and (= k (+ rn rn)) (< 1 cnt)) (recur rst (+ res (dec cnt)))
                        :else (let [b (some (fn [d]
                                              (when (zero? (mod (+ (first d) (first a))
                                                                k))
                                                d))
                                            rst)]
                                (if b 
                                  (recur (remove #(= % b) rst) (+ res (second b)))
                                  (recur rst res)))))))
      ]
  (println (- n rm-cnt))
  )
