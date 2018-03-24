(ns prog-math-puzzle.repeat-combination2)


(defn combs [xs ys]
  (for [x xs, y ys] (concat x y)))

(defn repeat-comb-width [coll n]
  (loop [res coll
         cnt n]
    (if (= cnt 1)
      res
      (recur (combs coll res) (dec cnt)))))

(defn repeat-combination [coll n]
  (if (= n 1)
    [coll]
    (repeat-comb-width (map vector coll) n)))


(repeat-combination [1 2 3] 2)
