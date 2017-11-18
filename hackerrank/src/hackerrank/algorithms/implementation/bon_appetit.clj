;;
;; https://www.hackerrank.com/challenges/bon-appetit/problem
;;
(ns hackerrank.algorithms.implementation.bon-appetit
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [[n i-not-eat] (str->nums (read-line))
      dat (str->nums (read-line))
      charge (Integer/parseInt (read-line))
      tcost (reduce + 0 dat)
      tcost-anna-eat (->> dat
                          (map-indexed vector)
                          (filter (fn [[i _]] (not= i i-not-eat)))
                          (reduce (fn [res [_ n]] (+ res n) ) 0))
      cost-anna-should-pay (/ tcost-anna-eat 2)
      over-paied (- charge cost-anna-should-pay)]
  (if (zero? over-paied)
    (println "Bon Appetit")
    (println over-paied)))

