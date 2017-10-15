(ns hackerrank.clj.crossword-101)


(defn vec->map [coll]
  {:x (first coll)
   :y (second coll)})

(defn sequential-of-pos?
  "座標の列collの各要素posが1ずつ増加しているかチェックする。
  pos .. :x or :y"
  [pos coll]
  (letfn [(iter [coll pre]
            (if (empty? coll)
              true
              (let [e (first coll)]
                (if (= (inc (pos pre)) (pos e))
                  (recur (rest coll) e)
                  false))))]
    (iter (rest coll) (first coll))))

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
         (filter #(sequential-of-pos? axis %)))))

;(for [e dat]
;  {:dat e
;   :vertical (filter #(= (first e) (first %)) dat)
;   :horizontal (filter #(= (second e) (second %)) dat)})


(def str-array ["LONDON" "DELHI" "ICELAND" "ANKARA"])

(defn get-empty-pos 
  [y coll]
  (->> (map (fn [a b] [a b]) (range (count coll)) coll)
       (filter (fn [[n o]] (= \- o)))
       (map (fn [[n o]] [n y]))))

(def dat (map vec->map (apply concat (for [i (range 10)]
                                      (get-empty-pos i (read-line))))))

(println (find-coordinate-along-axis :y dat))
(println (find-coordinate-along-axis :x dat))
