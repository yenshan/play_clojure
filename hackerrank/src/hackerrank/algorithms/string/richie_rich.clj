(ns hackerrank.algorithms.string.richie-rich
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn to-int [c] 
  (Integer/parseInt (str c)))

(defn lefth [string]
  (let [hlen (quot (count string) 2)]
    (apply str (take hlen string))))

(defn righth [string]
  (let [hlen (quot (count string) 2)]
    (apply str (take hlen (reverse string)))))

(defn center [string]
  (let [len (count string)
        hlen (quot len 2)]
    (subs string hlen (- len hlen))))


(defn replace-to-big [string]
  (let [pstr (map #(max (to-int %1) (to-int %2))
                  (lefth string)
                  (righth string))]
    (apply str (concat pstr (center string) (reverse pstr)))))


(defn count-diff [coll1 coll2]
  (reduce + (map #(if (not= %1 %2) 1 0) coll1 coll2)))

(defn first-index-of [f coll1 coll2]
  (loop [[a & rst1] coll1, [b & rst2] coll2, i 0]
    (cond (nil? a) nil
          (f a b) i
          :else (recur rst1 rst2 (inc i)))))

(defn index-for-replace [string]
  (let [l (lefth string)
        r (righth string)]
    (or (first-index-of #(and (not= %1 %2)
                              (not= %1 \9)
                              (not= %2 \9))
                              l r)
        (first-index-of #(not (= %1 %2 \9)) l r))))

(defn replace-nth [n c string]
  (letfn [(replace-c [s]
            (str (subs s 0 n) c (subs s (inc n))))]
    (apply str 
      (replace-c (lefth string))
      (center string)
      (reverse (replace-c (righth string))))))

(defn replace-to-9 [string]
  (if-let [i (index-for-replace string)]
    (replace-nth i \9 string)
    nil))

(defn replace-center [string]
  (if (empty? (center string))
    -1
    (apply str
           (lefth string)
           \9
           (reverse (righth string)))))

(defn count-diff-palendrome [string]
  (count-diff (lefth string) (righth string)))

(defn palendrome? [string]
  (= (lefth string) (righth string)))

(defn make-palendrom [string k]
  (cond (empty? string) -1
        (zero? k) (if (palendrome? string) string -1)
        :else (let [diffn (count-diff-palendrome string)]
                (cond (> diffn k) -1
                      (= diffn k) (replace-to-big string)
                      (= 1 k) (replace-center string)
                      :else (recur (replace-to-9 string) (- k 2))
                      ))))

(let [[n k] (str->nums (read-line))
      string (read-line)]
  (println (make-palendrom string k)))

