(ns hackerrank.algorithms.implementation.forming-a-magic-square
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn index->pos [i]
  [(mod i 3) (quot i 3)])

(def dat (->> [[4 8 2] [4 5 7] [6 1 6]]
              (reduce concat [])
              (map-indexed vector)
              (map (fn [[i n]] {:pos (index->pos i) :num n :flag true}))
              vec
              ))

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
  
(defn get-pos-sum-15 [f coll]
  (let [dat (filter #(f (:pos %)) coll)
        sum (->> dat (map :num) (reduce +))]
  (if (= 15 sum)
    (map :pos dat)
    '())))

(defn is-in? [p coll]
  (some #(= p %) coll))


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


(defn not-sum-15? [f coll]
  (let [dat (filter #(f (:pos %)) coll)
        sum (->> dat (map :num) (reduce +))
        wtbl (reduce (fn [res d] (if (:flag d) (inc res) res)) 0 dat)
        ]
    (if (not= 15 sum)
      {:sum sum :writable wtbl :dat dat}
      nil)))

(->> (check-not-writable-pos dat)
     (not-sum-15? (row-is? 1)))



(defn sum-when [f coll]
  (->> coll
       (filter #(f (:pos %)))
       (map :num)
       (reduce +)))

(sum-when (row-is? 2) dat)
(sum-when (col-is? 2) dat)
(sum-when diag-lt2rb? dat)
(sum-when diag-lb2rt? dat)

;
;(defn sum-diag-1 [coll]
;  (reduce + (for [i (range 3)]
;              (first (get coll (index i i))))))
;
;(defn sum-diag-2 [coll]
;  (reduce + (for [i (range 3)]
;              (first (get coll (index (- 2 i) i))))))
;
;(concat 
;  (for [i (range 3)]
;    (sum-row i dat))
;  (for [i (range 3)]
;    (sum-col i dat))
;  [(sum-diag-1 dat) (sum-diag-2 dat)])


  

