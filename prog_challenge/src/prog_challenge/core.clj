(ns prog-challenge.core)

(defn posnums
  ([] (posnums 1))
  ([n]
   (cons n (lazy-seq (posnums (inc n))))))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
