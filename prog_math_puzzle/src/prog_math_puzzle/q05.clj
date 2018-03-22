(ns prog-math-puzzle.q05)


(defn change_coin [money max_coins]
  (reduce +
          (for [m10 (range 0 (inc max_coins))
                m50 (range 0 (inc max_coins))
                m100 (range 0 (inc max_coins))
                m500 (range 0 (inc max_coins))
                :when (and (<= (+ m10 m50 m100 m500) max_coins)
                           (= money (+ (* 10 m10)
                                       (* 50 m50)
                                       (* 100 m100)
                                       (* 500 m500))))
                ]
            1)))

(println (change_coin 1000 15))
