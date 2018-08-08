(ns prog-math-puzzle.q15)


(def move-pattern (for [a (range 1 5)
                        b (range -1 -5 -1)]
                    [a b]))

(defn check-pattern [[a b :as p] ]
  (cond (= a b) 1
        (> a b) 0
        :else (apply +
                     (for [[va vb] move-pattern]
                       (check-pattern [(+ a va) (+ b vb)])))))

(println (check-pattern [0 10]))
