(ns hackerrank.functional-programming.ad-hoc.huge-gcd
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn gcd [a b]
  (cond (zero? b) a
        (> a b) (recur b (mod a b))
        (< a b) (recur a (mod b a))
        :else a))

(let [_ (read-line)
      n1 (reduce * 1N (str->nums (read-line)))
      _ (read-line)
      n2 (reduce * 1N (str->nums (read-line)))
      ]
  (println (str (mod (gcd n1 n2) 1000000007))))
