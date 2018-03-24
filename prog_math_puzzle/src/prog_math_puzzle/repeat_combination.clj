(ns prog-math-puzzle.repeat-combination)


(defn repeat-combination [xs n]
  (letfn [(comb-depth [cnt res]
            (if (zero? cnt)
              [res]
              (apply concat
                     (for [x xs]
                       (comb-depth (dec cnt)
                                   (conj res x))))))
          ]
    (if (= n 1)
      [xs]
      (comb-depth n []))))

(println (repeat-combination [1 2 3] 2))


