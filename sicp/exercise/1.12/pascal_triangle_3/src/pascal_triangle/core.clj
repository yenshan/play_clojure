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
  ([n] (pascal-triangle n [1] []))
  ([n a b]
    (if (zero? n)
      b
      (recur (dec n) (next-row a) (conj b a)))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (pascal-triangle 5)))
