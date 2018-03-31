(ns prog-math-puzzle.repeat-combination3)

;;
;; 深さ優先結合
;; 結合した結果をもらって、さらに組み合わせを行う
;;

(defn repeat-combination [xs n]
  (if (= n 0)
    [[]]
    (for [x xs
          y (repeat-combination xs (dec n))]
       (cons x y))))

(println (repeat-combination [1 2 3] 3))
