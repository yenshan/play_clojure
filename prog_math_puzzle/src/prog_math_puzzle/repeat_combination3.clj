(ns prog-math-puzzle.repeat-combination3)

;;
;; 深さ優先探索の組み合わせ作成その２
;; 結合した結果を戻り値にして返して、呼び元でその結果を組み合わせで結合する
;;

(defn combination [x ys]
  (map #(cons x %) ys))

(defn repeat-combination [xs n]
  (if (= n 0)
    [[]]
    (apply concat 
           (map #(combination % (repeat-combination xs (dec n))) 
                xs))))

(println (repeat-combination [1 2 3] 2))
