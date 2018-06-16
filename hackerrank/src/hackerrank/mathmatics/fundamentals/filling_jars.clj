;;
;; https://www.hackerrank.com/challenges/filling-jars/problem
;;
(ns hackerrank.mathmatics.fundamentals.filling-jars
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [[N M] (str->nums (read-line))]
  (let [dats (for [_ (range M)]
               (str->nums (read-line)))
        sum (reduce (fn [res [a b k]]
                      (+ res
                         (* k (+ 1 (- b a)))))
                    0
                    dats)
        ]
    (println (quot sum N))))

