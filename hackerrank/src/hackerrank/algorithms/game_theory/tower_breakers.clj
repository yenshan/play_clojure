;;
;; https://www.hackerrank.com/challenges/tower-breakers-1/problem
;;
(ns hackerrank.algorithms.game-theory.tower-breakers
  (:require [clojure.string :as s]))


(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [[N M] (str->nums (read-line))]
      (println (cond (= M 1) 2
                     (even? N) 2
                     :else 1)))))

