(ns prog-math-puzzle.repeat-combination)

;;
;; 深さ優先探索の組み合わせ作成
;; 結合した結果を関数の引数に渡す方式
;;

(defn combination [xs ys n]
  (if (<= n 0)
    [ys]
    (apply concat
           (map #(combination xs (conj ys %) (dec n))
                xs))))

(defn repeat-combination [xs n]
  (combination xs [] n))


