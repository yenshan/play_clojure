(ns pascal-triangle.core
  (:gen-class))


(defn next-row
  [row]
  (letfn [(add-one-both [coll] (concat [1] coll [1]))]
    (->> (partition 2 1 row)
         (map (fn [[a b]] (+ a b)))
         add-one-both
         vec)))

(defn pascal-triangle
  ([] (pascal-triangle [1]))
  ([col] (lazy-seq (cons col (pascal-triangle (next-row col))))))

   
;(defn pascal-triangle2
;  [x col n]
;    (if (zero? n)
;      col
;      (recur (next-row x) (cons x col) (dec n))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (take 5 (pascal-triangle))))
