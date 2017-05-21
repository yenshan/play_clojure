(ns fact-1.core
  (:gen-class))

(defn factorial
  [n]
  (if (= n 1)
    1
    (* n (factorial (dec n)))))


(defn -main
  [& args]
  (println (factorial 6)))
