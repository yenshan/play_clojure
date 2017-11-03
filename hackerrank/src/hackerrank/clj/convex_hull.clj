(ns hackerrank.clj.convex-hull
  (:require [clojure.string :as s]))


(defn get-vector
  [[x1 y1] coll]
  (map (fn [[x2 y2]] [(- x2 x1) (- y2 y1)])
       coll))

(defn check-dir [vec-coll]
  (loop [v vec-coll, res 0]
    (if (empty? v)
      res
      (let [[x y] (first v)
            x-bit (if (>= x 0) 2r0001 2r0010)
            y-bit (if (>= y 0) 2r0100 2r1000)]
        (recur (rest v) (bit-or res x-bit y-bit))))))

(defn perimeter
  [[x1 y1] [x2 y2]]
  (Math/sqrt (+ (Math/pow (- x2 x1) 2)
                (Math/pow (- y2 y1) 2))))

(defn perimeter-around 
  "coll should be vector"
  [coll]
  (loop [d (conj coll (first coll)),
         sum 0]
    (if (< (count d) 2)
      sum
      (recur (rest d)
             (+ sum (perimeter (first d) (second d)))))))

(defn convex_hull [pos-coll]
  (->> (for [e pos-coll]
         {:pos e :vecs (get-vector e (remove #(= % e) pos-coll))}
         )
       (filter #(not= 2r1111 (check-dir (:vecs %))))
       (map :pos)
       vec
       perimeter-around
       ))

(defn num->vec [nstr]
  (->> (s/split nstr #" ")
       (map #(Integer/parseInt %))))

(def N (Integer/parseInt (read-line)))

(->> (for [_ (range N)]
       (num->vec (read-line)))
     convex_hull
     println)

