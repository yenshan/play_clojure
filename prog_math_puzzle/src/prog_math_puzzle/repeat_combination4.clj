(ns prog-math-puzzle.repeat-combination4)

;;
;; 幅さ優先結合
;; 引数に結果を渡して結合を繰り返す方式で末尾最適あり
;;

(defn combination [xs ys n]
  (if (= n 0)
    ys
    (recur xs
           (for [x xs, y ys] (concat x y))
           (dec n))))

(defn repeat-combination [xs n]
  (combination (map vector xs) [[]] n))


(repeat-combination [1 2 3] 3)
