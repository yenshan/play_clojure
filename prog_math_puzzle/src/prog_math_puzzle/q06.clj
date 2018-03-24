(ns prog-math-puzzle.q06)


(defn corutt-num [start]
  (loop [n (+ 1 (* start 3))]
    (cond (= n start) true
          (= n 1) false
          :else (recur (if (even? n) 
                         (quot n 2)
                         (+ 1 (* n 3)))))))

(->> (for [n (range 2 (inc 10000) 2)]
                   (corutt-num n))
     (filter true?)
     count
     println)
