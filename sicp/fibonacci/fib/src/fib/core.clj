(ns fib.core
  (:gen-class))

(defn fib
  ([n] (fib 1 0 n))
  ([a b cnt]
   (if (zero? cnt)
     b
     (fib (+ a b) a (dec cnt)))))


(defn -main
  [& args]
  (println (fib 10)))
