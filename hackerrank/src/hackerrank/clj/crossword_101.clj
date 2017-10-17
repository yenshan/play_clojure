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

(defn get-empty-pos 
  [y coll]
  (->> (map (fn [a b] [a b]) (range (count coll)) coll)
       (filter (fn [[n o]] (= \- o)))
       (map (fn [[n o]] [n y]))))

(defn get-empty-boxes [dat]
  (map (fn [dat] {:len (count dat) :dat dat})
       (apply concat (for [a [:x :y]]
                       (find-coordinate-along-axis a dat)))))


(def test-boxes (list {:dat 'A :len 6} {:dat 'B :len 5} {:dat 'C :len 7} {:dat 'D :len 6})) 
(def test-strs ["LONDON" "DELHI" "ICELAND" "ANKARA"])

(defn all-patterns-fill-boxes
  ([strs boxes] (all-patterns-fill-boxes strs boxes []))
  ([strs boxes res]
   (if (empty? strs)
     res
     (let [r (for [e strs]
               (all-patterns-fill-boxes (remove #(= % e) strs) 
                                        (rest boxes)
                                        (conj res [e (first boxes)])))]
       (if (= (count strs) 1)
         r
         (apply concat r))))))

(defn patterns-of-put-str-in-box
  "空きボックスに文字列を当てはめてみた、すべてのパターンを返す。"
  [strs boxes]
  (letfn [(has-some-wrong-len? [coll]
            (some (fn [[string {len :len}]] (not= (count string) len)) coll))]
    (filter #(not (has-some-wrong-len? %)) (all-patterns-fill-boxes strs boxes))))


(defn read-lines->all-empty-positions
  [lines]
  (map vec->map
       (apply concat (for [i (range lines)]
                       (get-empty-pos i (read-line))))))

;;---
;;---
(def boxes (get-empty-boxes (read-line->all-empty-positions 10)))

(doseq [i (patterns-of-put-str-in-box test-strs boxes)]
  (println i))


