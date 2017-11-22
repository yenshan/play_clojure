;;
;; https://www.hackerrank.com/challenges/magic-square-forming/problem
;;
(ns hackerrank.algorithms.implementation.forming-a-magic-square
  (:require [clojure.string :as s]))

;;
;; This solution could not pass all test cases.
;; only passed Test Case #0, #21 #22
;;

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn index->pos [i]
  [(mod i 3) (quot i 3)])

(defn row-is? [n]
  (fn [pos]
    (= n (second pos))))

(defn col-is? [n]
  (fn [pos]
    (= n (first pos))))

(defn diag-lt2rb?
  "diag left-top to right-bottom."
  [p]
  (zero? (- (first p) (second p))))

(defn diag-lb2rt?
  "diag left-bottom to right-top"
  [p]
  (= 2 (+ (first p) (second p))))
  
(defn get-pos-sum-15
  "collを方向関数fでチェックしてsumが15になるなら、この方向のデータ列の座標を返す"
  [f coll]
  (let [dat (filter #(f (:pos %)) coll)
        sum (->> dat (map :num) (reduce +))]
  (if (= 15 sum)
    (map :pos dat)
    '())))

(defn is-in? [p coll]
  (some #(= p %) coll))

;; 3 x 3の魔方陣をチェックするときの方向関数
(def directions (concat (for [i (range 3)] (row-is? i))
                        (for [i (range 3)] (col-is? i))
                        (list diag-lt2rb?  diag-lb2rt?)))

(defn check-not-writable-pos [coll]
  (let [not-writable-pos (apply concat 
                                (for [f directions]
                                  (get-pos-sum-15 f coll)))]
    (map (fn [d]
           (if (is-in? (:pos d) not-writable-pos)
             (update d :flag (fn [_] false))
             d))
         coll)))


(defn not-sum-15?
  "collで方向関数fチェックしてsumが15でない場合に
   {合計値、書き換え可能セル数、データ烈}のmapを返す。
  それ以外はnilを返す"
  [f coll]
  (let [dat (filter #(f (:pos %)) coll)
        sum (->> dat (map :num) (reduce +))
        wtbl (reduce (fn [res d] (if (:flag d) (inc res) res)) 0 dat)
        ]
    (if (not= 15 sum)
      {:sum sum :writable wtbl :dat dat}
      nil)))


(defn get-modify-info
  "魔法陣の各方向でsumが15になるかチェックして、15にならない方向のデータ列のうち
  書き換え可能なセルが1のもののみを抽出して、その座標と値とデータ烈のsumのmapを返す"
  [coll]
  (let [dat1 (check-not-writable-pos coll)
        modify-target (->> (for [i directions]
                             (not-sum-15? i dat1))
                           (filter (fn [d] (= 1 (:writable d))))
                           set)
        ]
    (for [{sum :sum dat :dat}  modify-target]
      (let [mt (some (fn [d] (if (:flag d) d)) dat)]
        {:new-dat {:pos (:pos mt) :num (+ (:num mt) (- 15 sum)) :flag true}
         :dat mt}))))

(defn modify-matrix-with-info [modify-info dat]
  (map (fn [d]
         (let [new-d (some (fn [dd] 
                             (when (= (:dat dd) d)
                               (:new-dat dd))) 
                           modify-info)]
           (if new-d new-d d)))
       dat))

(defn forming-magic-square
  [square-dat]
  (loop [dat square-dat, res '()]
    (let [modify-info (set (get-modify-info dat))]
      (if (empty? modify-info)
        res
        (recur (modify-matrix-with-info modify-info dat)
               (concat res modify-info))))))

(def dat (->> (for [_ (range 3)] (str->nums (read-line)))
              (reduce concat [])
              (map-indexed vector)
              (map (fn [[i n]] {:pos (index->pos i) :num n :flag true}))
              vec
              ))

(->> (forming-magic-square dat)
     (map (fn [{:keys [new-dat dat]}]
            (- (:num new-dat) (:num dat))))
     (map #(Math/abs %))
     (reduce +)
     println
     )


