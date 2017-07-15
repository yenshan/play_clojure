(ns pascal-triangle.core
  (:gen-class))

(defn my-patition
   [[a b :as col]]
   (when (and a b)
     (cons [a b] (my-patition (rest col))))) 


(defn next-row
  [row]
  (letfn [(add-1-both-ends [coll] (concat [1] coll [1]))]
    (->> (my-patition row)
         (map (fn [[a b]] (+ a b)))
         add-1-both-ends
         vec)))

(defn pascal-triangle
  ([] (pascal-triangle [1]))
  ([col] (lazy-seq (cons col (pascal-triangle (next-row col))))))

   
(defn -main
  [& args]
  (println (take 5 (pascal-triangle))))
