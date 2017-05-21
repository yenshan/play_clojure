(ns fact-2.core
  (:gen-class))

(defn factorial
  ([n] (factorial 1N 1 n))
  ([product counter max-count] (if (> counter max-count)
                                 product
                                 (recur (* counter product)
                                            (inc counter)
                                            max-count))))

(defn factorial'
  [n]
  (letfn [(iter [product counter]
            (if (> counter n)
              product
              (recur (* counter product) (inc counter))))]
    (iter 1N 1)))

(defn -main
  [& args]
  (println (factorial 10))
  (println (factorial' 10)))
