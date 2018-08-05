(ns prog-math-puzzle.q13)


(def st1 "READ")
(def st2 "WRITE")
(def st3 "TALK")
(def st4 "SKILL")


(defn coll->num [coll]
  (loop [[x & xs] (reverse coll)
         base 1
         res 0]
    (if (nil? x)
      res
      (recur xs
             (* 10 base)
             (+ res (* base x)))))) 

(defn str->num [m st]
  (-> (map #(get m %) st)
      (coll->num)))

(defn ng? [m]
  (or (= 0 (get m \R))
      (= 0 (get m \W))
      (= 0 (get m \T))
      (= 0 (get m \S))))

(defn expected-result? [m]
  (if (ng? m)
    false
    (let [[n1 n2 n3 n4] (map #(str->num m %) [st1 st2 st3 st4])]
      (= (+ n1 n2 n3) n4))))

(defn num-comb
  ([st nums] (num-comb st nums {}))
  ([[x & xs] ys res]
   (if (nil? x)
     (if (expected-result? res) 1 0)
     (apply + (for [n ys]
                (num-comb xs
                          (remove #(= % n) ys)
                          (assoc res x n)))))))

(println (num-comb (vec (set (str st1 st2 st3 st4)))
                   (range 0 10)))

