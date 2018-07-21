;;
;;https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
;;

(ns hackerrank.interview-preparation-kit.arrays.array-left-rotation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn rot-left [coll d]
  (let [n (mod d (count coll))
        l (take n coll)
        r (drop n coll)]
    (concat r l)))

(defn print-coll [coll]
  (loop [[d & rst] coll]
    (when d
      (print d)
      (if rst 
        (do (print " ") (recur rst))
        (println "")))))

(let [[n d] (str->nums (read-line))
      a (str->nums (read-line))
      ]
  (print-coll (rot-left a d)))
      

