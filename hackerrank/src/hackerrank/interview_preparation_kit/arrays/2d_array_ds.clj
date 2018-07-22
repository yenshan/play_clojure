;;
;;https://www.hackerrank.com/challenges/2d-array/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
;;

(ns hackerrank.interview-preparation-kit.arrays.2d-array-ds
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn add [[x1 y1] [x2 y2]] [(+ x1 x2) (+ y1 y2)])

(defn xy->i [[x y]]
  (+ x (* y 6)))

(defn hourglass-sum [arr p]
  (let [poss [[-1 -1][0 -1][1 -1]
              [-1 1][0 1][1 1][0 0]]]
    (apply + (for [d poss]
               (->> (add p d)
                    xy->i
                    (get arr))))))

(let [arr (->> (for [_ (range 6)]
                 (str->nums (read-line)))
               (apply concat)
               to-array)
      sums (->> (for [y (range 1 5)
                      x (range 1 5)]
                  (hourglass-sum arr [x y])))
      ]
  (println (apply max sums)))
