(ns prog-math-puzzle.q05)


(reduce +
        (for [m10 (range 0 (inc 15))
              m50 (range 0 (inc 15))
              m100 (range 0 (inc 15))
              m500 (range 0 (inc 15))
              :when (and (<= (+ m10 m50 m100 m500) 15)
                         (= 1000 (+ (* 10 m10)
                                    (* 50 m50)
                                    (* 100 m100)
                                    (* 500 m500))))
              ]
          1))

