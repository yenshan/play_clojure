(ns hackerrank.algorithms.string.richie-rich
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn to-int [c] 
  (Integer/parseInt (str c)))

(defn ->palendrome [fun string]
  (let [len (count string)
        hlen (quot len 2)
        left (take hlen string)
        right (take hlen (reverse string))
        center (subs string hlen (- len hlen))
        left2 (map fun left right)
        right2 (map fun right left)
        ]
    (->> (concat left2 center (reverse right2))
         (apply str))))

(defn count-diff [coll1 coll2]
  (->> (map #(not= %1 %2) coll1 coll2)
       (filter true?)
       count))

(defn diff-for-palendrome [string]
  (let [hlen (quot (count string) 2)
        prehalf (take hlen string)
        rearhalf (take hlen (reverse string))]
    (count-diff prehalf rearhalf)))

(defn replace-first-if [pred to coll1 coll2]
  (loop [[a & rst1] coll1
         [b & rst2] coll2
         res []]
    (if (nil? a)
      res
      (if (pred a b)
        (concat (conj res to) rst1)
        (recur rst1 rst2 (conj res a))))))

(defn replace-first 
  [scoll ccoll match replacement]
  )
  
(defn replace-both-when
  "前半と後半の数字を比較して最初だけ両方をcに置き換える"
  [fun string c]
  (let [len (count string)
        hlen (quot len 2)
        left (take hlen string)
        right (take hlen (reverse string))
        center (subs string hlen (- len hlen))
        left2 (replace-first-if fun c left right)
        right2 (replace-first-if fun c right left)
        center (subs string hlen (- len hlen))
        ]
    (->> (concat left2 center (reverse right2))
         (apply str))))

(let [
      ;[_ k] (str->nums (read-line))
      [_ k] [8 4]
      ;string (read-line)
      string "1111911"
      diffn (diff-for-palendrome string)
      ]
  (cond (< k diffn) (println -1)
        (= k diffn) (println (->palendrome #(max (to-int %1) (to-int %2))
                                           string))
        (= 1 k (count string)) (println \9)
        :else 
        ;; 前半と後半の数字が違うもので、どちらかかが9のものを最初に置換する
        ;; 全部の数字に対して行って、変更した数をkから引く
        (let [tmp1 (->palendrome (fn [a b]
                                   (if (or (= a \9) (= b \9)) \9 a))
                                 string)
              rst-k (- k (count-diff string tmp1))
              ]
          (println rst-k tmp1)
          (if (zero? rst-k)
            (println tmp1)
            ;; 前半と後半の数字が違うもので9が入ってないものに
            ;; 両方を9に置換する。
            (let [tmp2 (replace-both-when not= tmp1 \9)
                  rst2-k (- rst-k 2)
                  ]
              (println rst2-k tmp2)
              (if (zero? rst2-k)
                (println tmp2)
                (let [tmp3 (replace-both-when = tmp2 \9)
                      rst3-k (- rst2-k 2)
                      ]
                  (println tmp3)
                  (if (zero? rst3-k)
                    (println tmp3)
                    (println rst3-k tmp3)
                  ))))))))

