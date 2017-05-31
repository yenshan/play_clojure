(ns fib.core
  (:gen-class))

(defn fib
  ([n] (fib 1N 0N 0N 1N n))
  ([a b p q cnt]
   (cond 
     (zero? cnt) b
     (even? cnt) (recur a
                        b
                        (+ (* p p) (* q q))
                        (+ (* 2 p q) (* q q))
                        (/ cnt 2))
     :else (recur (+ (* b q) (* a q) (* a p))
                  (+ (* b p) (* a q))
                  p
                  q
                  (- cnt 1)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
