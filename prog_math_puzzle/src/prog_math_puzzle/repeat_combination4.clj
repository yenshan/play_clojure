(ns prog-math-puzzle.repeat-combination4)


(defn combs [xs ys]
  (for [x xs, y ys] (concat x y)))

(defn repeat-comb-width [xs ys n]
  (if (= n 0)
    ys
    (recur xs (combs xs ys) (dec n))))

(defn repeat-combination [xs n]
  (repeat-comb-width (map vector xs) [[]] n))


(repeat-combination [1 2 3] 3)
