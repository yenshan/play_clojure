(ns fib.core
  (:gen-class))

(defn fib
  ([n] (fib 1N 0N n))
  ([a b cnt]
   (if (zero? cnt)
     b
     (recur (+ a b) a (dec cnt)))))


(defn -main
  [& args]
  (println (fib 10)))
