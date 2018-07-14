;;
;; https://www.hackerrank.com/challenges/possible-path/problem
;;

(ns hackerrank.mathmatics.fundamentals.possible-path
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn gcd [a b]
  (cond (= a b) a
        (> a b) (recur b (- a b))
        :else (recur a (- b a))))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [[a b x y] (str->nums (read-line))]
      (println (if (= (gcd a b) (gcd x y))
                 "YES"
                 "NO")))))

