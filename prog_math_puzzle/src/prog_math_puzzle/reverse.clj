(ns prog-math-puzzle.reverse)

(defn my-reverse [coll]
  (if (empty? coll)
    []
    (concat (my-reverse (rest coll)) 
            (take 1 coll))))

(defn my-reverse2 [coll]
  (loop [[a & rst] coll
         res []]
    (if (nil? a)
      res
      (recur rst (cons a res)))))


