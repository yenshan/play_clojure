(ns segment.core
  (:gen-class))

(defn make-point [x y] [x y])
(defn x-point [p] (first p))
(defn y-point [p] (last p))

(defn make-segment [start end] [start end])
(defn start-segment [s] (first s))
(defn end-segment [s] (last s))

(defn midpoint-segment [s]
  (let [ss (start-segment s)
        es (end-segment s)]
    (make-point (/ (+ (x-point ss) (x-point es)) 2)
                (/ (+ (y-point ss) (y-point es)) 2))))

(defn -main
  [& args]
  (let [seg (make-segment (make-point 10 20)
                          (make-point 20 30))]
    (println "segment is " seg)
    (println "midpoint is " (midpoint-segment seg))))
