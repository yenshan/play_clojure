;;
;; https://www.hackerrank.com/challenges/taum-and-bday/problem
;;
(ns hackerrank.algorithms.implementation.taum-and-bday
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [[B W] (str->nums (read-line))
          [X Y Z] (str->nums (read-line))
          price-x (cond (< X Y) X
                        (<= X (+ Y Z)) X
                        :else (+ Y Z))
          price-y (cond (< Y X) Y
                        (<= Y (+ X Z)) Y
                        :else (+ X Z))
          ]
      (println (+ (* price-x B) (* price-y W))))))
      
