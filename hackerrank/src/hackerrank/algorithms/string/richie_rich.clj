(ns hackerrank.algorithms.string.richie-rich
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn to-int [c] 
  (Integer/parseInt (str c)))

(defn lefth [string]
  (let [hlen (quot (count string) 2)]
    (vec (take hlen string))))

(defn righth [string]
  (let [hlen (quot (count string) 2)]
    (vec (take hlen (reverse string)))))

(defn center [string]
  (let [len (count string)
        hlen (quot len 2)]
    (subs string hlen (- len hlen))))

(defn ->strdat [string]
  {:left (lefth string)
   :right (righth string)
   :center (center string)})

(def NOT-POSSIBLE {:left "" :right "" :center "-1"})

(defn replace-center [strdat]
  (if (empty? (:center strdat))
    NOT-POSSIBLE
    (assoc strdat :center "9")))

(defn replace-to-big [strdat]
  (let [pstr (map #(max (to-int %1) (to-int %2))
                  (:left strdat)
                  (:right strdat))]
    {:left pstr 
     :right pstr 
     :center (:center strdat)}
    ))

(defn replace-diff-to-9 [strdat]
  (letfn [(replace-9 [s1 s2]
            (map (fn [a b] (if (and (not= a b) (or (= a \9)
                                                   (= b \9)))
                             \9 a))
                 s1 s2))]
    {:left (replace-9 (:left strdat) (:right strdat))
     :right (replace-9 (:right strdat) (:left strdat))
     :center (:center strdat)}
    ))

(defn replace-to-9-from-left [strdat]
  (letfn [(replace-9 [s1 s2]
            (loop [[a & rst1] s1 [b & rst2] s2, res1 [], res2 [], cnt 0]
              (if (nil? a)
                [res1 res2]
                (let [cnd (and (zero? cnt) (not= a \9) (not= b \9))]
                  (recur rst1
                         rst2
                         (conj res1 (if cnd \9 a))
                         (conj res2 (if cnd \9 b))
                         (if cnd (inc cnt) cnt)
                         )))))
          ]
    (let [rs (replace-9 (:left strdat) (:right strdat))]
      {:left (first rs)
       :right (second rs)
       :center (:center strdat)}
      )))

(defn count-diff [f coll1 coll2]
  (reduce + (map #(if (f %1 %2) 1 0) coll1 coll2)))

(defn count-palendrome [f strdat]
  (count-diff f (:left strdat) (:right strdat)))


(defn palendrome? [strdat]
  (= (:left strdat) (:right strdat)))

(defn every-is-9? [strdat]
  (every? #(= % \9) 
          (concat (:left strdat) (:center strdat) (:right strdat))))

(defn to-str [strdat]
  (apply str 
         (concat (:left strdat)
                 (:center strdat)
                 (reverse (:right strdat)))))

(defn make-palendrom [string kk]
  (letfn [(iter [strdat k]
            (let [diffn (count-palendrome not= strdat)]
              (cond 
                (zero? k) (if (palendrome? strdat) strdat NOT-POSSIBLE)
                (< k diffn) NOT-POSSIBLE 
                (= k diffn) (replace-to-big strdat)
                (= k 1) (replace-center strdat)
                :else (recur (replace-to-9-from-left strdat) (- k 2))
                )))
          ]
    (let [sd (->strdat string)
          diff9n (count-palendrome #(and (not= %1 %2) (or (= %1 \9) (= %2 \9))) sd)
          sd2 (if (> diff9n 0) (replace-diff-to-9 sd) sd)
          ]
      (to-str (iter sd2 (- kk diff9n))))))

(let [[n k] (str->nums (read-line))
      string (read-line)]
  (if (<= n k) 
    (println (apply str (take n (repeat \9))))
    (println (make-palendrom string k))))

