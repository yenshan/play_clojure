;;
;; https://www.hackerrank.com/challenges/happy-ladybugs/problem
;;
(ns hackerrank.algorithms.implementation.happy-ladybugs
  (:require [clojure.string :as s]))


(defn lonely-lady-in? [coll]
  (some (fn [[c n]] (= 1 n))
        (loop [[a & rst] coll, prev nil, res []]
          (if (nil? a)
            (conj res prev)
            (if (= a (first prev))
              (recur rst [a (inc (second prev))] res)
              (recur rst [a 1] (if prev (conj res prev) res)))))))

(let [g (Integer/parseInt (read-line))]
  (doseq [_ (range g)]
    (let [n (Integer/parseInt (read-line))
          board (read-line)
          cnt-colors (reduce (fn [res d]
                              (if-let [pd (get res d)]
                                (update res d inc)
                                (assoc res d 1)))
                            {}
                            (filter #(not= % \_) board))
          cnt-space (count (filter #(= % \_) board))
          ]
      (println (cond (empty? cnt-colors) "YES"
                     (some #(= % 1) (vals cnt-colors)) "NO"
                     (= 1 (count cnt-colors)) "YES"
                     (and (zero? cnt-space) (lonely-lady-in? board)) "NO"
                     :else "YES")))))
                     
