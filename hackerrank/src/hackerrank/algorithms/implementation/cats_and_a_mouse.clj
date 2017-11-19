;;
;; https://www.hackerrank.com/challenges/cats-and-a-mouse/problem
;; 
(ns hackerrank.algorithms.implementation.cats-and-a-mouse
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def n (Integer/parseInt (read-line)))

(doseq [_ (range n)]
  (let [[cat1 cat2 mouse] (str->nums (read-line))
        d1 (Math/abs (- cat1 mouse))
        d2 (Math/abs (- cat2 mouse))]
    (cond (> d1 d2) (println "Cat B")
          (> d2 d1) (println "Cat A")
          :else (println "Mouse C"))))


