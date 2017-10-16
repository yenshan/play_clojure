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


(defn get-empty-pos 
  [y coll]
  (->> (map (fn [a b] [a b]) (range (count coll)) coll)
       (filter (fn [[n o]] (= \- o)))
       (map (fn [[n o]] [n y]))))

;(def dat (map vec->map (apply concat (for [i (range 10)]
;                                      (get-empty-pos i (read-line))))))

;(println (find-coordinate-along-axis :y dat))
;(println (find-coordinate-along-axis :x dat))


(def test-boxes (list ['A 6] ['B 5] ['C 7] ['D 6])) 
(def test-strs ["LONDON" "DELHI" "ICELAND" "ANKARA"])

(println boxes)

(defn find-fit-box [len coll]
  (some (fn [d] (if (= len (second d)) d nil))
        coll))

(find-fit-box 7 test-boxes)

;(defn all-patterns [strs boxes]
;  (let [s (first strs)
;        fb (find-fit-box (count s) boxes)]
;    (when (not (nil? fb))
;      (cons [fb s] (all-patterns (rest strs)
;                                 (remove #(= % fb) boxes))))))


(def test-dat1 [1 2 3 4])
(def test-dat2 [\a \b \c \d])

(defn all-patterns 
  ([coll1 coll2] (all-patterns coll1 coll2 []))
  ([coll1 coll2 res]
   (if (empty? coll1)
     res
     (let [r (for [e coll1]
               (all-patterns (remove #(= % e) coll1) 
                             (rest coll2)
                             (conj res [e (first coll2)])))]
       (if (= (count coll1) 1)
         r
         (apply concat r))))))

(all-patterns test-dat1 test-dat2)




