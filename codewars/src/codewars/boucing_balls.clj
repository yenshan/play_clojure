(ns codewars.boucing-balls)

(defn bouncing-balls [h bounce window]
  (if (and (> h 0) (> bounce 0) (< bounce 1) (< window h))
    (loop [nh (* h bounce)
           cnt 0]
      (println nh cnt)
      (if (> nh window)
        (recur (* nh bounce) (+ cnt 2))
        (inc cnt)))
    -1))

(println (bouncing-balls 3 0.66 1.5))
(println (bouncing-balls 30 0.75 1.5))
