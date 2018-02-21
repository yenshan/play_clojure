;;
;; https://www.hackerrank.com/challenges/missing-numbers/problem
;;
(ns hackerrank.algorithms.search.missing-number
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn cnt-number-frequency [coll]
  (reduce (fn [res d]
            (if (get res d)
              (update res d inc)
              (assoc res d 1)))
          {}
          coll))

(defn check-freq-diff [freq1 freq2]
  (loop [[a & rst] (vec freq1), res {}]
    (if (nil? a)
      res
      (if-let [n (get freq2 (first a))]
        (if (not= n (second a))
          (recur rst (assoc res (first a) 1))
          (recur rst res))
        (recur rst (assoc res (first a) 1))))))

(let [_ (read-line)
      A (str->nums (read-line))
      _ (read-line)
      B (str->nums (read-line))
      freq-A (cnt-number-frequency A)
      freq-B (cnt-number-frequency B)
      res (vec (sort (map first (check-freq-diff freq-B freq-A))))
      ]
  (println (s/replace (str res) #"[\[\]]" "")))

  
