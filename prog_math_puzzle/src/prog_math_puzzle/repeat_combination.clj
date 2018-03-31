(ns prog-math-puzzle.repeat-combination)

;;
;; 深さ優先結合
;; 引数に結合結果を渡し、結合繰り返す方式
;;

(defn combination [xs ys n]
  (if (<= n 0)
    [ys]
    (apply concat
           (for [x xs] (combination xs (conj ys x) (dec n))))))

(defn repeat-combination [xs n]
  (combination xs [] n))


(repeat-combination [1,2,3] 3)

