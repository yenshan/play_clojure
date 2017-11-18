;;
;; https://www.hackerrank.com/challenges/sock-merchant/problem
;;
(ns hackerrank.algorithms.implementation.sock-merchant
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def _ (read-line))

(let [socks (map-indexed vector (str->nums (read-line)))]
  (println 
    (loop [[[_ a] & rst] socks, res 0]
      (if (empty? rst)
        res
        (let [pair (some (fn [[i e]] (when (= e a) i)) rst)]
          (if pair
            (recur (remove (fn [[i e]] (= i pair)) rst) (inc res))
            (recur rst res)))))))
