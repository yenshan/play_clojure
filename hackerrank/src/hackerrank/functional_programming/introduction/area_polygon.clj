;;
;;https://www.hackerrank.com/challenges/lambda-march-compute-the-area-of-a-polygon/problem
;;
(ns hackerrank.functional-programming.introduction.area-polygon)

(defn sum-products
  ([f coll] (sum-products f (conj coll (first coll)) 0))
  ([f coll res]
   (if (< (count coll) 2)
     res
     (recur f (rest coll) (+ res (f (first coll) (second coll)))))))

(defn area-polygon [coll]
  (let [sum-l (sum-products #(* (first %1) (second %2)) coll)
        sum-r (sum-products #(* (first %2) (second %1)) coll)]
    (/ (Math/abs (- sum-l sum-r)) 2.0)))


(defn str->pair [string]
  (let [d (str/split string #" ")]
    [(Integer/parseInt (first d)) (Integer/parseInt (second d))]))

(defn read-pair-numbers [n]
  (vec (for [i (range n)]
    (str->pair (read-line)))))

(->> (Integer/parseInt (read-line))
     read-pair-numbers
     area-polygon
     println)
