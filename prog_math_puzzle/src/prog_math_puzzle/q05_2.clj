
(defn change_coin_patterns [money coins max-coins]
  (cond (zero? money) 1
        (empty? coins) 0
        (zero? max-coins) 0
        :else (reduce + 
                      (for [uses (range 0 (inc max-coins))]
                        (change_coin_patterns (- money (* uses (first coins)))
                                              (rest coins)
                                              (- max-coins uses))))))

(println (change_coin_patterns 1000 [10 50 100 500] 15))


