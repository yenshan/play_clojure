(ns prog-math-puzzle.q04)


(defn cut [len m]
  (letfn [(iter [n]
            (cond (>= n len) 0
                  (< n m) (+ 1 (iter (+ n n)))
                  :else (+ 1 (iter (+ n m)))))
          ]
    (iter 1)))

(cut 20 3) ;-> 8
(cut 100 5) ;-> 22

