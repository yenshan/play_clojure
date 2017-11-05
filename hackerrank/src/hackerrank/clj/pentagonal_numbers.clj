(ns hackerrank.clj.pentagonal-numbers)

(defn two-side-points [n]
  (condp = n
    1 1
    (+ 1 (* 2 (dec n)))))

(defn P [n]
  (condp = n
    1 1
    (- (+ (* 5 (dec n))
          (P (dec n)))
       (two-side-points (dec n)))))

(doseq [_ (range (Integer/parseInt (read-line)))]
  (println (P (Integer/parseInt (read-line)))))

