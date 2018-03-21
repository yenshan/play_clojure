(ns prog-math-puzzle.q04)


(defn reverse-cards [from step cards]
  (loop [at from
         res cards]
    (if (> at 100)
      res
      (recur (+ at step)
             (if (= :BACK (get res at))
               (assoc res at :FRONT)
               (assoc res at :BACK))))))

(let [cards (reduce (fn [res d]
                      (assoc res d :BACK))
                    {}
                    (range 1 (inc 100)))
      res (loop [step 2
                 res cards]
            (let [ncards (reverse-cards step step res)]
              (if (= ncards res)
                res
                (recur (inc step) ncards))))
      ]
  (->> res
       (filter #(= :BACK (second %)))
       (map first)
       sort
       println)
  )

      
