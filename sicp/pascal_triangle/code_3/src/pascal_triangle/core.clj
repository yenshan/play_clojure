(ns pascal-triangle.core
  (:gen-class))


(defn next-row
    [row]
    (letfn [(add-1-both-ends [coll] (concat [1] coll [1]))]
      (->> (partition 2 1 row)
           (map (fn [[a b]] (+ a b)))
           add-1-both-ends
           vec)))

(defn pascal-triangle
  ([n] (pascal-triangle [1] [] n))
  ([x col n]
    (if (zero? n)
      col
      (recur (next-row x) (conj col x) (dec n)))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (pascal-triangle 5)))
