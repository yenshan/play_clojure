;;
;; https://www.hackerrank.com/challenges/crosswords-101/problem
;;
(ns hackerrank.clj.crossword-101
  (:require [clojure.string :as s]))

(def pos {:x first :y second})
(def opposit {:x :y :y :x})

(defn split-not-sequential [axis boxes]
  (letfn [(iter [coll res]
            (if (< (count coll) 2)
              (concat res coll)
              (let [a (first coll)
                    b (second coll)]
                (if (= (inc ((pos axis) a))
                       ((pos axis) b))
                  (recur (rest coll) (conj res a))
                  (list (conj res a) (rest coll))))))]
    (iter boxes [])))

(defn split-loop [axis boxes]
  (letfn [(iter [coll ret]
            (let [res (split-not-sequential axis coll)]
              (if (= coll res)
                (conj ret res)
                (recur (second res)
                       (conj ret (first res))))))]
    (iter boxes [])))

(defn find-coordinate-along-axis
  "与えられた軸axisに添うシーケンシャルな座標の列を抽出する。
  axis .. :x or :y
  coll ..  {:x _ :y _} の配列"
  [axis coll]
  (let [oaxis (opposit axis)]
    (->> (for [e coll]
           (filter #(= ((pos oaxis) e) 
                       ((pos oaxis) %))
                   coll))
         set
         (map #(split-loop axis %))
         (apply concat)
         (filter #(not= 1 (count %))))))

(defn get-empty-boxes [dat]
  (map (fn [dat] {:len (count dat) :dat dat})
       (apply concat (for [a [:x :y]]
                       (find-coordinate-along-axis a dat)))))


(defn all-patterns-fill-boxes
  "すべてのboxesにすべての文字列を入れたすべてのパターンを返す。
  文字列の長さとboxesの長さが合っているかどうかは無視している。"
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


(defn make-pos-word-seq
  "空きboxの座標に文字列を入れた結果のシーケンスを返す"
  [strs boxes]
  (for [ptn (patterns-of-put-str-in-box strs boxes)]
    (for [word ptn]
      (map (fn [c p]
             {:char c :pos p})
           (first word) (:dat (second word))))))

(defn filter-crossword
  "クロスワードになっていないものを排除する
  （同じ座標のboxに違う文字が入っているパターンを排除する）"
  [coll]
  (reduce (fn [res d]
            (if (= res nil)
              nil
              (let [k (:pos d)
                    c (get res k)]
                (if (and c (not= c (:char d)))
                  nil
                  (assoc res k (:char d))))))
          {}
          coll))

(defn print-result [coll]
  (when coll
    (doseq [y (range 10)
            x (range 10)]
      (do 
        (when (and (> y 0) (zero? x))
          (print "\n"))
        (let [d (get coll [x y])]
          (if d
            (print d)
            (print "+")))))))

(defn get-empty-pos 
  [y coll]
  (->> (map (fn [a b] [a b]) (range (count coll)) coll)
       (filter (fn [[n o]] (= \- o)))
       (map (fn [[n o]] [n y]))))

;; -----------------------
;; read datas
;; -----------------------
(defn read-empty-positions
  [lines]
  (apply concat (for [i (range lines)]
                  (get-empty-pos i (read-line)))))

(def all-empty-pos (read-empty-positions 10))
(def boxes (get-empty-boxes all-empty-pos))
(def strs (s/split (read-line) #";"))

(doseq [ptn (make-pos-word-seq strs boxes)]
  (print-result 
    (filter-crossword (apply concat ptn))))


