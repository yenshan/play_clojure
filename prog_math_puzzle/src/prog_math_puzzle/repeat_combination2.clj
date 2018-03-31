(ns prog-math-puzzle.repeat-combination2)


(defn combs [xs ys]
  (for [x xs, y ys] (concat x y)))

(defn repeat-comb-width [xs n]
  (if (= n 1)
    xs
    (combs xs (repeat-comb-width xs (dec n)))))

(defn repeat-combination [coll n]
  (repeat-comb-width (map vector coll) n))


(repeat-combination [1 2 3] 3)
