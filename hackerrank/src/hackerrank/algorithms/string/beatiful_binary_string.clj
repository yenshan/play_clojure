;;
;; https://www.hackerrank.com/challenges/beautiful-binary-string/problem
;;
(ns hackerrank.algorithms.string.beatiful-binary-string
  (:require [clojure.string :as s]))


(defn rps-until-end [src tgt string]
  (loop [s string]
    (let [ns (s/replace s src tgt)]
      (if (= ns s)
        s
        (recur ns)))))

(let [_ (read-line)
      string (read-line)]
  (->> string
       (rps-until-end #"01010" "01110")
       (rps-until-end #"010" "000")
       (map #(not= %1 %2) string)
       (filter true?)
       count
       println))
