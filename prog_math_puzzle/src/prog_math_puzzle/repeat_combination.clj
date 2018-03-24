(ns prog-math-puzzle.repeat-combination)

(defmulti combination (fn [x ys] (if (seq? ys) :seq :val)))

(defmethod combination :seq [x ys]
  (for [y ys] (cons x y)))

(defmethod combination :val [x ys]
  (for [y ys] (list x y)))

(defn repeat-combination [coll n]
  (if (= n 1)
    coll
    (apply concat 
           (for [e coll]
             (combination e (repeat-combination coll (dec n)))))))

(repeat-combination [1 2 3] 3)
