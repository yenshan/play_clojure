;;
;; https://www.hackerrank.com/challenges/chocolate-feast/problem
;;
(ns hackerrank.algorithms.implementation.chocolate-feat
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [t (Integer/parseInt (read-line))]
  (doseq [_ (range t)]
    (let [[n c m] (str->nums (read-line))
          buy-chocos (quot n c)
          total-chocos (loop [wraps buy-chocos, new-chocos buy-chocos, sum 0]
                         (let [exch-chocos (quot wraps m)]
                           (if (zero? exch-chocos)
                             (+ sum new-chocos)
                             (recur (+ (mod wraps m) exch-chocos)
                                    exch-chocos
                                    (+ sum new-chocos)))))
          ]
      (println total-chocos))))


