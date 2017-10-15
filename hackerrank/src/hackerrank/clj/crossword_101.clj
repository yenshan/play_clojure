(ns hackerrank.clj.crossword-101)



(def str-array ["LONDON" "DELHI" "ICELAND" "ANKARA"])

(defn get-empty-pos 
  ([coll y] (get-empty-pos coll y 0 []))
  ([coll y x res]
   (if (empty? coll)
     res
     (if (= \- (first coll))
       (recur (rest coll) y (inc x) (conj res [x y]))
       (recur (rest coll) y (inc x) res)))))

(defn vec->map [coll]
  {:x (first coll)
   :y (second coll)})

(def dat (map vec->map '([1 0] [1 1] [1 2] [1 3] [2 3] [3 3] [4 3] [5 3] [1 4] [5 4] [1 5] [5 5] [5 6] [2 7] [3 7] [4 7] [5 7] [6 7] [7 7] [5 8] [5 9])))

;(def dat (apply concat (for [i (range 10)]
;                         (get-empty-pos (read-line) i))))



(defn sequential-along-axis?
  ([axis coll] (-sequential? axis (rest coll) (first coll)))
  ([axis coll pre]
   (if (empty? coll)
     true
     (let [e (first coll)]
       (if (= (inc (axis pre)) (axis e))
         (recur axis (rest coll) e)
         false)))))

(defn axis-complement [a]
  (condp = a
    :x :y
    :y :x
    :else a))

(defn find-coordinate-along-axis
  "与えられた軸axisに添うシーケンシャルな座標の列を抽出する。
  axis .. :x or :y
  coll ..  {:x _ :y _} の配列"
  [axis coll]
  (let [oaxis (axis-complement axis)] ; opposit axis
    (->> (for [e coll]
           (filter #(= (oaxis e) (oaxis %)) coll))
         set
         (filter #(not= 1 (count %)))
         (filter #(sequential-along-axis? axis %)))))
         
(find-coordinate-along-axis :y dat)
  

(for [e dat]
  {:dat e
   :vertical (filter #(= (first e) (first %)) dat)
   :horizontal (filter #(= (second e) (second %)) dat)})



