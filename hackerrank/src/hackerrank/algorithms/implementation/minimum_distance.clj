;;
;; https://www.hackerrank.com/challenges/minimum-distances/problem
;;
(ns hackerrank.algorithms.implementation.minimum-distance
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(let [_ (read-line)
      dat (str->nums (read-line))
      idat (map-indexed vector dat)
      min-d (loop [[a & rst] idat, res -1]
              (if (empty? rst)
                res
                (if-let [td (some (fn [d]
                                 (when (= (second a) (second d))
                                   d))
                               rst)]
                  (let [dist (- (first td) (first a))]
                    (recur rst (if (or (= res -1) (< dist res)) dist res)))
                  (recur rst res))))
      ]
  (println min-d)
  )


