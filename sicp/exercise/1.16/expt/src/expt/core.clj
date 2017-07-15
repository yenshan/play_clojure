(ns expt.core
  (:gen-class))

(defn expt
  ([b n] (expt b n 1))
  ([b n a] (cond 
             (zero? n) a
             (even? n) (recur (* b b) (/ n 2) a)
             :else (recur b (dec n) (* b a)))))

(defn -main
  [& args]
  (println (expt 2 10)))
