(ns prog-math-puzzle.n-ary)

(defn n_ary [number base] 
  (loop [n number,
         res []]
    (if (zero? n)
      res
      (recur (quot n base)
             (cons (mod n base) res)))))

(defn n_ary_str [number base]
  (apply str (n_ary number base)))


(defn n_ary2 [number base]
  (if (zero? number)
    []
    (let [quotient (quot number base)
          remainder (mod number base)]
    (conj (n_ary2 quotient base) remainder))))

(defn n_ary' [number base]
  (if (zero? number)
    []
    (conj (n_ary' (quot number base) base)
          (mod number base))))

(n_ary2 10 8)
