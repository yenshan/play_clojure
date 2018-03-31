(ns prog-math-puzzle.repeat-combination2)

;;
;; 幅優先結合
;; 結合した結果をもらって、さらに結合する方式
;;

(defn combination [xs n]
  (if (= n 1)
    xs
    (for [x xs
          y (combination xs (dec n))]
      (concat x y))))

(defn repeat-combination [coll n]
  (combination (map vector coll) n))


(repeat-combination [1 2 3] 3)
