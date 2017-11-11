;;
;; https://www.hackerrank.com/challenges/plus-minus/problem 
;;
(ns hackerrank.algorithms.warmup.plus-minus
  (:require [clojure.string :as s]))


(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def N (Integer/parseInt (read-line)))

(defn print-result [n {:keys [positive negative zero]}]
  (println (double (/ positive n)))
  (println (double (/ negative n)))
  (println (double (/ zero n))))


(->> (str->nums (read-line))
     (reduce (fn [res n]
               (cond (> n 0) (update res :positive inc)
                     (< n 0) (update res :negative inc)
                     :else (update res :zero inc)))
             {:positive 0 :negative 0 :zero 0})
     (print-result N))

