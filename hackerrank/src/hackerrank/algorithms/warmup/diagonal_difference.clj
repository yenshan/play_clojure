;;
;; https://www.hackerrank.com/challenges/diagonal-difference/problem
;;
(ns hackerrank.algorithms.warmup.diagonal-difference
  (:require [clojure.string :as s]))


(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn index [x y w]
  (+ x (* y w)))

(def N (Integer/parseInt (read-line)))


(let [nseq (apply concat (for [_ (range N)]
                            (str->nums (read-line))))
      pri-diag (reduce + 
                       (for [i (range N)]
                         (nth nseq (index i i N))))
      sec-diag (reduce +
                       (for [i (range N)]
                         (nth nseq (index i (- N 1 i) N))))
      ]
  (println (Math/abs (- pri-diag sec-diag))))

