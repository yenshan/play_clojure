;;
;;https://www.hackerrank.com/challenges/mark-and-toys/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
;;
(ns hackerrank.interview-preparation-kit.sorting.mark-and-toys
  (:require [clojure.string :as s]))


(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn -main []
  (let [[_ budget] (str->nums (read-line))
        prices (str->nums (read-line))
        toys (loop [[x & xs] (sort prices)
                    m 0
                    cnt 0]
               (if (or (nil? x) (>= (+ m x) budget))
                 cnt
                 (recur xs (+ m x) (inc cnt))))
        ]
    (println toys)))

(-main)
