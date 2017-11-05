(ns hackerrank.clj.pentagonal-numbers)

(defn two-side-points [^long n]
  (if (= n 1)
    1
    (+ 1 (* 2 (dec n)))))

(defn P [^long N]
  (loop [n N, res 0]
    (if (= n 1)
      (inc res)
      (recur (dec n)
             (-> res
                 (+ (* 5 (dec n)))
                 (- (two-side-points (dec n))))))))

(doseq [_ (range (Integer/parseInt (read-line)))]
  (println (P (Integer/parseInt (read-line)))))

