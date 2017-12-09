;;
;; https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem
;;
(ns hackerrank.algorithms.implementation.jumping-on-the-clouds
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [_ (read-line)
      clouds (to-array (str->nums (read-line)))
      steps (loop [i 0, cnt 0]
              (if (>= i (dec (count clouds)))
                cnt
                (if (= 1 (get clouds (+ i 2)))
                  (recur (inc i) (inc cnt))
                  (recur (+ i 2) (inc cnt)))))
      ]
  (println steps))
