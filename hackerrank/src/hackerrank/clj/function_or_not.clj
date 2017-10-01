#!/usr/bin/env clj

;;
;; https://www.hackerrank.com/challenges/functions-or-not/problem
;;


(ns hackerrank.clj.function-or-not
  (:require [clojure.string :as str]))

(defn str->pair [string]
  (let [d (str/split string #" ")]
    [(Integer/parseInt (first d)) (Integer/parseInt (second d))]))
  
(let [nt (Integer/parseInt (read-line))]
  (loop [i 0]
    (when (< i nt)
      (let [nxy (Integer/parseInt (read-line))
            datas (for [i (range nxy)]
                   (str->pair (read-line)))
            check (reduce (fn [m d] 
                            (cond (= m nil) nil
                                  (contains? m (first d)) nil
                                  :else (assoc m (first d) (second d))))
                          {}
                          datas)]
        (println (if check "YES" "NO")))
      (recur (inc i)))))
