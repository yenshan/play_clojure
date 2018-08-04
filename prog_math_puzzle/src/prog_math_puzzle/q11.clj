(ns prog-math-puzzle.q11)

(defn fib-seq [a b]
  (lazy-seq (cons a (fib-seq b (+ a b)))))

(defn num->coll [n]
  (loop [x n
         res []]
    (if (= x 0)
      (reverse res)
      (recur (quot x 10)
             (conj res (mod x 10))))))

(->> (fib-seq 1N 1N)
     (filter (fn [x]
               (let [sd (apply + (num->coll x))]
                 (zero? (mod x sd)))))
     (drop-while #(<= % 144))
     (take 5)
     println)
