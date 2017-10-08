(ns hackerrank.clj.sierpinski-triangle)

(defn next-row [coll]
  (vec 
    (reduce (fn [res [x y]] 
              (conj res 
                    [(dec x) (inc y)]
                    [x (inc y)]
                    [(inc x) (inc y)]))
            #{}
            coll)))

(defn triangle [pos]
  (cons pos (lazy-seq (triangle (next-row pos)))))

(defn triangle-pos-list [pos n]
  (take n (triangle [pos])))

(defn sierpinski-triangle [w h x y]
  (let [dot-tri (reduce concat (triangle-pos-list [x y] h))]
    (for [iy (range h)
          ix (range w)]
      (if (some #(= % [ix iy]) dot-tri)
        \1
        \_))))


(doall (map-indexed (fn [i e]
               (when (zero? (mod i 63)) (print "\n"))
               (print e))
             (sierpinski-triangle 63 32 (quot 63 2) 0)))
