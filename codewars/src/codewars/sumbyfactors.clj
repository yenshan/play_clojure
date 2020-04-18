(ns codewars.sumbyfactors)


(defn abs [x]
  (if (< x 0)
    (- x)
    x))

(defn prim? [n]
  (empty?  (for [x (range 2 (inc (quot n 2)))
                 :when (zero? (mod n x))]
             x)))

(defn sum-of-divided [lst]
  (let [prim-list (->> lst 
                       (map abs)
                       (apply max)
                       (+ 1)
                       (range 2)
                       (filter prim?))]
    (for [p prim-list
          :let [pdiv (filter #(zero? (mod % p)) lst)]
          :when (not (empty? pdiv))]
      [p (apply + pdiv)])))


