(ns codewars.play-digit)

(defn pow [p n]
  (apply * (repeat p n)))

(defn dig-pow [n p]
  (let [pnum (->> (str n)
                  (map #(- (int %) (int \0)))
                  (map pow (iterate inc p))
                  (apply +))]
    (if (zero? (mod pnum n))
      (quot pnum n)
      -1)))

